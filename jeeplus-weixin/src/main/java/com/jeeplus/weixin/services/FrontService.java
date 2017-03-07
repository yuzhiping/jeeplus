package com.jeeplus.weixin.services;

import java.io.OutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.jeeplus.weixin.common.pagination.Page;
import com.jeeplus.weixin.common.pagination.PageHelper;
import com.jeeplus.weixin.common.pagination.PageInfo;
import com.jeeplus.weixin.dto.IndexModel;
import com.jeeplus.weixin.dto.RegisterModel;
import com.jeeplus.weixin.dto.ResetPwdModel;
import com.jeeplus.weixin.dto.VerifyCodeModel;
import com.jeeplus.weixin.entities.*;
import com.jeeplus.weixin.utils.Constants;
import com.jeeplus.weixin.utils.IOUtil;
import com.jeeplus.weixin.utils.ObjectUtil;
import com.jeeplus.weixin.utils.SendsmsUtil;
import com.jeeplus.weixin.utils.cache.RedisUtil;
import com.jeeplus.weixin.utils.json.JsonUtil2;
import com.jeeplus.weixin.utils.json.JsonUtil3;
import org.apache.commons.lang3.StringUtils;
import org.json.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

/**
 * Created by lzh  
 */
@Service
public class FrontService extends BaseService{


    
	public CommonModel getLoginUser(String phoneNo, String password, HttpServletRequest request) {
		
		UserInfoModel user = null;

         
		CommonModel commonModel=new CommonModel();
		commonModel.setFlag(true);
		
		if(StringUtils.isNotEmpty(phoneNo) && StringUtils.isNotEmpty(password)){
           
        	user = userInfoModelMapper.selectByPhoneNo(phoneNo);
        	commonModel.setFlag(true);
        	commonModel.setObj(user);
        	
            if (null != user){
                /** 密码不对则登录失败 */
                if (!password.equalsIgnoreCase(user.getPwd())){
                	commonModel.setFlag(false);
                    commonModel.setMessage("登录失败,用户名或者密码错误!");
                }else {
	                	
               	    String ua = ((HttpServletRequest) request).getHeader("user-agent").toLowerCase();  
              	    if (ua.indexOf("micromessenger") > 0) { 
              	    	
  	                      UserInfoModel userInfoModel=	(UserInfoModel) session.getAttribute(Constants.USER_SESSION_LOGIN);
  		                 
  	                      if((userInfoModel!=null && userInfoModel.getOpenid()!=null && !userInfoModel.getOpenid().equals(user.getOpenid()))){
  	                          commonModel.setFlag(false);
  	                          commonModel.setMessage("登录失败，该账号非微信已绑定的账号!");
  		                  }
  	                      
  	                 
              	    }
                
                }
                
            }
            
   
        }
        return commonModel;
	}

	public Boolean save_registr(RegisterModel registerModel) {
		
		//插入注册用户
		registerModel.setRefferno(registerModel.getRefereePhone());
		registerModel.setUserlevel(new Byte("2"));
		
		
		UserInfoModel userInfoModel=new UserInfoModel();
		ObjectUtil.copy(registerModel, userInfoModel);
		userInfoModelMapper.insert(userInfoModel);
		
		//插入推荐人信息
		ReffererInfoModel refferer=new ReffererInfoModel();
		refferer.setPhoneno(registerModel.getPhoneno());
		refferer.setRefferno(registerModel.getRefereePhone());
		String sql="select count(*) from tmk_user_info where phoneNo='"+registerModel.getRefereePhone()+"'";
		
		//向未注册的推推荐人用户发送短信提醒
		if(dictModelMapper.selectCount(new SQLAdapterModel(sql))==0){
		   SendsmsUtil.Send(registerModel.getRefereePhone(), "温馨提示:您已被"+registerModel.getPageNo()+"设置为推荐人,请及时注册领取奖励http://rent.botann.com");
		}
		
		//初始化用户账户
		UserAccountModel userAccountModel=new UserAccountModel();
		userAccountModel.setAccountuser(userInfoModel.getRecordid());
		userAccountModelMapper.insert(userAccountModel);
		
		
		
		try {
			  reffererInfoModelMapper.insert(refferer);
		} catch (Exception e) {
			// TODO: handle exception
			 return false;
		}
	  
	    
	    return true;
 
	 
	}

	//同一个ip或手机号1分钟只能请求一次发送短信
	public CommonModel getVerifyCode(final HttpServletRequest request, final String mobile) {
		
		CommonModel commonModel=new CommonModel();
    	
    	int vrifyCode = (int)((Math.random()*9+1)*100000);
    	
    	VerifyCodeModel verifyCodeModel=null;
 
		String mobileJsonString= RedisUtil.getString(mobile);
		String	ipJsonString=RedisUtil.getString( getRemoteHost(request));

     	if(mobileJsonString==null && ipJsonString==null){ 
    		
 
    		verifyCodeModel=new VerifyCodeModel();

	    	verifyCodeModel.setMobile(mobile);
	    	verifyCodeModel.setVrifyCode(vrifyCode+"");
	    	verifyCodeModel.setIp(getRemoteHost(request));

	    	final String ip= getRemoteHost(request);
	    	
	    	String  verifyCodeJson= JsonUtil3.object2json(verifyCodeModel);
            RedisUtil.setString(mobile,120 ,verifyCodeJson);
            RedisUtil.setString(getRemoteHost(request),120,verifyCodeJson);
 
	     	SendsmsUtil.Send(mobile,"您的验证码是：" + vrifyCode + "。请不要把验证码泄露给其他人。如非本人操作，可不用理会！ ");
	    	System.out.println("vrifyCode====>"+vrifyCode);
 
	    	
	        commonModel.setFlag(true);
    		commonModel.setMessage("获取验证码成功");
	    	
    	}else{
    		commonModel.setFlag(false);
    		commonModel.setMessage("获取验证码失败:每次操作间隔不能小于2分钟");
    	}  
    	return commonModel;
	}

	public CommonModel dynamiccodeVerify(String dynamiccode,String mobile) {
		// TODO Auto-generated method stub
	 	CommonModel   commonModel=new CommonModel();
    	commonModel.setFlag(false);
    	
    	if(StringUtils.isEmpty(dynamiccode)){
    		commonModel.setMessage("请输入验证码");
    		return commonModel;
    	}else{
    		
			String verifyCodeJsonString=RedisUtil.getString(mobile);
			JSONObject jsonObject= JsonUtil2.stringToJSONObject(verifyCodeJsonString);
			VerifyCodeModel verifyCodeModel= (VerifyCodeModel) JsonUtil2.getBean(VerifyCodeModel.class,jsonObject);
    		
    		if(verifyCodeModel==null){
    			commonModel.setMessage("验证码已经过期");
    	  		return commonModel;
    	  	}else if(verifyCodeModel!=null && dynamiccode.equals(verifyCodeModel.getVrifyCode())){
    	  		commonModel.setFlag(true);
    	  		commonModel.setMessage("验证码不正确");
    	  		return commonModel;
    	  	}else{
    	  		commonModel.setMessage("验证码不正确");
    	  		return commonModel;
    	  	}	
    		
    	}
	}

	/**
	 * 验证用户信息的唯一性
	 * @param commonModel
	 * @return
	 */
	public CommonModel uniqueVerify(CommonModel commonModel) {
		// TODO Auto-generated method stub
		
		String sql;
		commonModel.setFlag(true);
 	
		if("phoneno".equals(commonModel.getName())){
			
			sql="select count(*) from tmk_user_info where phoneNo='"+commonModel.getValue()+"'";
			
 
			if(dictModelMapper.selectCount(new SQLAdapterModel(sql))>0){
				commonModel.setFlag(false);
				commonModel.setMessage("该电话已经注册过了");
			}
	 
		}else if("resetpwd_phoneno".equals(commonModel.getName())){
			
			sql="select count(*) from tmk_user_info where phoneNo='"+commonModel.getValue()+"'";
		 
			if(dictModelMapper.selectCount(new SQLAdapterModel(sql))==0){
				commonModel.setFlag(false);
				commonModel.setMessage("该电话还未注册");
			}
		}
		

		
		return commonModel;
	}

   /**
     * 重置密码
     * @return
     */ 
	public CommonModel resetPass(HttpSession session,ResetPwdModel resetPwdModel) {
		// TODO Auto-generated method stub
		
		
		CommonModel commonModel=new CommonModel();
		commonModel.setFlag(true);
		commonModel.setMessage("修改密码成功");
		
		//VerifyCodeModel verifyCodeModel = (VerifyCodeModel) EhcacheUtil.get("verifyCodeCache", resetPwdModel.getPhoneno());
		String verifyCodeJsonString=RedisUtil.getString(resetPwdModel.getPhoneno());
		JSONObject jsonObject=JsonUtil2.stringToJSONObject(verifyCodeJsonString);
		VerifyCodeModel verifyCodeModel= (VerifyCodeModel) JsonUtil2.getBean(VerifyCodeModel.class,jsonObject);
	
		if(verifyCodeModel==null){
			commonModel.setFlag(false);
			commonModel.setMessage("修改密码失败:验证码已经失效");
			return commonModel;
		}
		
		try {
			if(resetPwdModel.getDynamiccode().endsWith(verifyCodeModel.getVrifyCode())&&
					resetPwdModel.getPhoneno().equals(verifyCodeModel.getMobile())){
				
				UserInfoModel userInfoModel=userInfoModelMapper.selectByPhoneNo(resetPwdModel.getPhoneno());
				

				 if(resetPwdModel.getPwd().equals(userInfoModel.getPwd())){
						
						commonModel.setFlag(false);
						commonModel.setMessage("修改密码失败:新密码不能和原来的一样");	
				}else{
					    userInfoModel.setPwd(resetPwdModel.getPwd());
				     	userInfoModelMapper.updateByPrimaryKeySelective(userInfoModel);
				}
		

			}else{
				
				commonModel.setFlag(false);
				commonModel.setMessage("修改密码失败:验证码不正确");	
			} 
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			
			commonModel.setFlag(false);
			commonModel.setMessage("修改密码失败");
			
		}
	
		
		return commonModel;
	}
	
	public PageInfo selectSupplyCarPageList(IndexModel indexModel) {
		
	 
		    //默认城市为杭州
			if(indexModel.getCarsupplycity()==null||"".equals(indexModel.getCarsupplycity())){
				indexModel.setCarsupplycity("330100");
			}
		
	        PageInfo pageInfo = null;
	        int pageSize = 15;
	        int pageNum = 1;
	        if(null != indexModel && indexModel.getCurPage() != 0){
	            pageNum = indexModel.getCurPage();
	        }
	        PageHelper.startPage(pageNum, pageSize, true);
	        Page<IndexModel> pages = supplyCarModelMapper.selectSupplyCarList(indexModel);
	        if(null != pages){
	            pageInfo = new PageInfo(pages);
	        }
	        
 
		
		return pageInfo;
	}

	//防止通过了Apache,Squid等反向代理软件就不能获取到客户端的真实IP地址了。
	public String getRemoteHost(HttpServletRequest request){
	    String ip = request.getHeader("x-forwarded-for");
	    if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)){
	        ip = request.getHeader("Proxy-Client-IP");
	    }
	    if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)){
	        ip = request.getHeader("WL-Proxy-Client-IP");
	    }
	    if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)){
	        ip = request.getRemoteAddr();
	    }
	    return ip.equals("0:0:0:0:0:0:0:1")?"127.0.0.1":ip;
	}

	
	//设置首页数据
	public void setIndexData(IndexModel indexModel, Model model) {
 
	    //默认城市为杭州
		if(indexModel.getCarsupplycity()==null||"".equals(indexModel.getCarsupplycity())){
			indexModel.setCarsupplycity("330100");
		} 
		PageInfo pageInfo = selectSupplyCarPageList(indexModel);
		
        model.addAttribute("carAppTypeList",dictUtil.getDictListBycategory("CarAppType"));
        
        if(!StringUtils.isEmpty(indexModel.getSupplycarapptype().toString())){
        	  model.addAttribute("vehicleappname",dictUtil.getDictNameByValue("CarAppType",indexModel.getSupplycarapptype()));
        }
      
 
        indexModel.setCityName(citiesModelMapper.selectByPrimaryKey(indexModel.getCarsupplycity()).getName());
        model.addAttribute("indexModel",indexModel);
        
        model.addAttribute("pageInfo", pageInfo);
		
	}

	 //根据百度地图返回的城市名获取数据库中的编号
	public String switchCtiy(CitiesModel citiesModel) {
		// TODO Auto-generated method stub
 
		 
		citiesModel=citiesModelMapper.selectByCityName(citiesModel);
		
		return citiesModel.getId();
	}

	
	//验证推荐人信息
	public CommonModel refferVerify(ReffererInfoModel reffererInfoModel) {
		// TODO Auto-generated method stub
		
		CommonModel commonModel=new CommonModel();
		commonModel.setFlag(true);
		commonModel.setMessage("输入推荐人成功");
		
		
		if(reffererInfoModel.getPhoneno().equals(reffererInfoModel.getRefferno())){
			commonModel.setFlag(false);
			commonModel.setMessage("抱歉,不能添加本人为推荐人");
	 
		}else{
			
			String sql="select count(*) from tmk_user_info where phoneNo='"+reffererInfoModel.getRefferno()+"'";
			

			if(dictModelMapper.selectCount(new SQLAdapterModel(sql))==0){
				commonModel.setFlag(true);
				commonModel.setMessage("您的推荐人未注册,请及时告知进行注册领取奖励");
			}
		}
		
 	
		return commonModel;
	}

	public void getFace(HttpServletResponse response,
			UserInfoModel userInfoModel) {
		// TODO Auto-generated method stub
		  try {    
 
		        byte data[] = IOUtil.GenerateImageBytes(getLoginUser().getUserpic());
		         
		        response.setContentType("image/jpg"); //设置返回的文件类型     
		        OutputStream os = response.getOutputStream();
		      
		        os.write(data);    
		        os.flush();    
		        os.close();
		  
		       } catch (Exception e) {    
		           e.printStackTrace();    
		       }   
	}
	
	public PageInfo selectCityNameList(CitiesModel citiesModel) {
		 
        PageInfo pageInfo = null;
        int pageSize = 50;
        int pageNum = 1;
        if(null != citiesModel && citiesModel.getCurPage() != 0){
            pageNum = citiesModel.getCurPage();
        }
        PageHelper.startPage(pageNum, pageSize, true);
        Page<CitiesModel> pages = citiesModelMapper.selectCityName();
        if(null != pages){
            pageInfo = new PageInfo(pages);
        }
        return pageInfo;
	}
	
    //设置城市列表页面数据
    public void setCitiesData(PageInfo pageInfo,
			CitiesModel citiesModel, Model model) {
		// TODO Auto-generated method stub
 
			
		model.addAttribute("citiesModel",citiesModel);
        
        model.addAttribute("pageInfo", pageInfo);
		
	 }


}
