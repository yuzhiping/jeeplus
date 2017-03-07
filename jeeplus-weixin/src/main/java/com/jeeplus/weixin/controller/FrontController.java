package com.jeeplus.weixin.controller;

 

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.jeeplus.weixin.common.pagination.PageInfo;
import com.jeeplus.weixin.dto.IndexModel;
import com.jeeplus.weixin.dto.RegisterModel;
import com.jeeplus.weixin.dto.ResetPwdModel;
import com.jeeplus.weixin.entities.CitiesModel;
import com.jeeplus.weixin.entities.CommonModel;
import com.jeeplus.weixin.entities.ReffererInfoModel;
import com.jeeplus.weixin.entities.UserInfoModel;
import com.jeeplus.weixin.services.FrontService;
import com.jeeplus.weixin.utils.Constants;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

 
 

/**
 * Created by lzh  
 */
@Controller
@RequestMapping("/front")
public class FrontController extends BaseController {

    private final String prefix = "front/";

    @Autowired
    private FrontService frontService;
 
     /**
     * 首页
     * @return
     */ 
    @RequestMapping("/index")
    public String index(IndexModel indexModel, Model model){
    	
    	
        
        Cookie cookie=getCookieByName(request, "location");
          //定位成功
         if(cookie!=null && StringUtils.isNotEmpty(cookie.getValue())){
           //if(1==1){
        	 
        	 //用户选择城市
        	 if(StringUtils.isEmpty(indexModel.getCarsupplycity())){
        		  indexModel.setCarsupplycity(cookie.getValue());
        	 }

	         frontService.setIndexData(indexModel,model);
            
        	 return prefix + "index";
       
        }else{
        	
            
		    frontService.setIndexData(indexModel,model);
        	
        	if(request.getQueryString()==null){
	    		 return  "redirect:location";
	    	}else{
	    	    return "redirect:location?"+request.getQueryString();
	    	}
        }
    
    	 
    }
	    
    
   
	     /**
	     * 首页加载更多
	     * @return
	     */ 
	    @RequestMapping("/laod_more")
	    @ResponseBody
	    public PageInfo laod_more(IndexModel indexModel){
	    	
	        PageInfo pageInfo = frontService.selectSupplyCarPageList(indexModel);
           
	        return pageInfo;
	    }
	    
   

	    /**
	     * 登录页面
	     * @return
	     */
	    @RequestMapping("/checklogin")
	    @ResponseBody
	    public CommonModel checklogin(@RequestParam(value = "phoneNo",required = false)String phoneNo,
									  @RequestParam(value = "password",required = false)String password,
									  HttpSession session, Model model){
	    	
 
        	CommonModel commonModel = frontService.getLoginUser(phoneNo,password,request);
        	
        	
            if (commonModel.getFlag()){
            	
            	 session.setAttribute(Constants.USER_SESSION_LOGIN,commonModel.getObj());

            
            } 
 
            return commonModel;
	    }
	    
	 
	    @RequestMapping("/location")
	    public String location(IndexModel indexModel,Model model){
	    	
	    	     model.addAttribute("urlparam", request.getQueryString());
	    		 
	    	     return prefix + "location";
	    
	    }
	    
	     /**
	     * 进入登入页面
	     * @return
	     */ 
	    @RequestMapping("/login")
	    public String login(Model model){

            //如果在微信登入先根据openid获取用户放在session中
       	    String ua = ((HttpServletRequest) request).getHeader("user-agent").toLowerCase();  
      	    if (ua.indexOf("micromessenger") > 0) { 
 
      	    	   	    FwhloginService fwhloginService = SpringContext.getBean("fwhloginServiceImpl");
	        	    	try {
							fwhloginService.getWxLoginuser(request, response);
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} 
      	 
      	    }  
	    	
	        return prefix + "login";
	    }
	    
	     /**
	     * 进入注册页面1
	     * @return
	     */ 
	    @RequestMapping("/register")
	    public String register(Model model){
 
	        return prefix + "register1";
	    }
	     /**
	     * 进入注册2页面
	     * @return
	     */ 
	    @RequestMapping(value="/register2",method=RequestMethod.GET)
	    public String register2(String phoneno,Model model){
	        
	    	model.addAttribute("phoneno",phoneno);
	          
	        return prefix + "register2";
	    }
	     /**
	     * 进入忘记密码页面
	     * @return
	     */ 
	    @RequestMapping("/forgetPass")
	    public String forgetPass(Model model){

	        return prefix + "forgetPass";
	    }
	    
	    
	     /**
	     * 重置密码
	     * @return
	     */ 
	    @RequestMapping("/resetPass")
	    @ResponseBody
	    public  CommonModel resetPass(ResetPwdModel resetPwdModel){

	    	
	    	  return  frontService.resetPass(session,resetPwdModel);
	    	
	 
	    }
	    
	    
	    //根据百度地图返回的城市名获取数据库中的编号
	    @RequestMapping("/switchCtiy")
	    @ResponseBody
	    public  String switchCtiy(CitiesModel citiesModel){

	    	  return   frontService.switchCtiy(citiesModel);
	    }
	    
	    /**
	     * 进入城市列表
	     * @return
	     */ 
	    @RequestMapping("/sitePage")
	    public String sitePage(CitiesModel citiesModel,IndexModel indexModel,Model model){
	    	
	    	frontService.setIndexData(indexModel,model);
	    	
	    	PageInfo pageInfo = frontService.selectCityNameList(citiesModel);
	    	
	        frontService.setCitiesData(pageInfo,citiesModel,model);
	    	
	        return prefix + "sitePage";
	    }
	    
	    
	    
	    
	    /**
	     * 保存注册
	     * @return
	     */ 
	    @RequestMapping("/save_registr")
	    @ResponseBody
	    public Boolean  save_registr(RegisterModel registerModel){
	    	
	    	   registerModel.setUserpic(Constants.NULL_AVATAR);
	    	   return  frontService.save_registr(registerModel);
  
	    }
	    
	    /**
	     * 验证用户信息的唯一性
	     * @param commonModel
	     * @return
	     */
	    @RequestMapping("/uniqueVerify")
	    @ResponseBody
	    public CommonModel   uniqueVerify(CommonModel commonModel){
 	    	
	    	   return  frontService.uniqueVerify(commonModel);
	    }
	    
	    /**
	     * 验证推荐人信息
	     * @param reffererInfoModel
	     * @return
	     */
	    @RequestMapping("/refferVerify")
	    @ResponseBody
	    public CommonModel   refferVerify(ReffererInfoModel reffererInfoModel){

 	    	
	    	   return  frontService.refferVerify(reffererInfoModel);
	    }
	    
	    /**
	     * 动态码验证
	     * @param mobile
	     * @return
	     */
	    @RequestMapping("/dynamiccodeVerify")
	    @ResponseBody
	    public CommonModel   dynamiccodeVerify(@RequestParam(value = "dynamiccode")String dynamiccode,
	    		@RequestParam(value = "mobile")String mobile){
	    	
	           
	    	return   frontService.dynamiccodeVerify(dynamiccode,mobile);
	    	
	    }
	    /**
	     * 获取手机验证码
	     * @return
	     */
	    @RequestMapping("/getVerifyCode")
	    @ResponseBody
	    public CommonModel  getVerifyCode(String mobile){
	    
	    	return frontService.getVerifyCode(request,mobile);
	 
	    }
	    
	  
	    
		
		/// 把base64加密后的图片字符串转bytes,转为流返回 
	    @RequestMapping("/getFace")
		public void getFace(UserInfoModel userInfoModel){
			
	    	  frontService.getFace(response,userInfoModel);
			
		}
	    
	    
	    /**
	     * 进入收益工具页1
	     * @return
	     */ 
	    @RequestMapping("/incomeToolfirst")
	    public String incomeToolfirst(){
	
	        return "incomeTool/index";
	    }
	    /**
	     * 进入收益工具页2
	     * @return
	     */ 
	    @RequestMapping("/incomeToolsecond")
	    public String incomeToolsecond(){
	
	        return "incomeTool/incomeShow";
	    }
	    /**
	     * 进入收益工具页3
	     * @return
	     */ 
	    @RequestMapping("/incomeToolthree")
	    public String incomeToolthree(){
	
	        return "incomeTool/incomeTool";
	    }
    
	    
}
