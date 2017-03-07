package com.jeeplus.weixin.services;

import com.jeeplus.weixin.dto.ResetPwdModel;
import com.jeeplus.weixin.entities.CommonModel;
import com.jeeplus.weixin.entities.ReffererInfoModel;
import com.jeeplus.weixin.entities.SQLAdapterModel;
import com.jeeplus.weixin.entities.UserInfoModel;
import com.jeeplus.weixin.utils.SendsmsUtil;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;


@Service
public class PersonService extends BaseService{
 
	
 
	
	public void setPersonData(Integer userid, Model model) {
		// TODO Auto-generated method stub
		
	   UserInfoModel userInfoModel = userInfoModelMapper.selectByPrimaryKey(userid);

	 
	   model.addAttribute("userInfoModel",userInfoModel);
	 
	   
	   
	}

	public CommonModel uptPersonInfo(UserInfoModel userInfoModel) {

		// TODO Auto-generated method stub
		CommonModel commonModel = new  CommonModel();
		commonModel.setFlag(true);
		commonModel.setMessage("修改个人信息成功");
		
		try {
			
			userInfoModelMapper.updateByPrimaryKeySelective(userInfoModel);
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			commonModel.setFlag(false);
			commonModel.setMessage("修改个人信息时错误");
		}
		
		
		return commonModel;
	}

	public CommonModel uptrefferInfo(UserInfoModel userInfoModel) {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		CommonModel commonModel = new  CommonModel();
		commonModel.setFlag(true);
		commonModel.setMessage("修改推荐人信息成功");
		
		try {
			
           
			String sql="select count(0) from tmk_user_info where phoneNo='"+getLoginUser().getPhoneno() +"' and refferNo='"+userInfoModel.getRefferno()+"'";
			int count=dictModelMapper.selectCount(new SQLAdapterModel(sql));
			if(count==0){
				 //更新用户的推荐人手机号
				userInfoModel.setRefferno(userInfoModel.getRefferno());
				userInfoModelMapper.updateByPrimaryKeySelective(userInfoModel);
				
				//添加推荐人历史记录
				ReffererInfoModel reffererInfoModel=new ReffererInfoModel();
				reffererInfoModel.setRefferno(userInfoModel.getRefferno());
	            reffererInfoModel.setPhoneno(getLoginUser().getPhoneno());
	        	reffererInfoModelMapper.insert(reffererInfoModel);
 
			} 	 

		    sql="select count(*) from tmk_user_info where phoneNo='"+userInfoModel.getRefferno()+"'";
			
			//向未注册的推推荐人用户发送短信提醒
			if(dictModelMapper.selectCount(new SQLAdapterModel(sql))==0){
				 SendsmsUtil.Send(userInfoModel.getRefferno(), "温馨提示，您已被"+getLoginUser().getPhoneno()+"设置为推荐人，请及时注册领取奖励http://www.botann.com/btkjsite/front/login");
			}
		
 
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			commonModel.setFlag(false);
			commonModel.setMessage("修改推荐人信息时错误");
		}
		
		
		return commonModel;
	}

	public CommonModel saveUpdatePass(ResetPwdModel resetPwdModel, Model model) {
		// TODO Auto-generated method stub
		CommonModel commonModel = new  CommonModel();
		commonModel.setFlag(true);
		commonModel.setMessage("修改密码成功");
		
		try {
 
				 if(resetPwdModel.getPwd().equals(getLoginUser().getPwd())){
						
						commonModel.setFlag(false);
						commonModel.setMessage("修改密码失败:新密码不能和原来的一样");	
				}else{
					    getLoginUser().setPwd(resetPwdModel.getPwd());
				     	userInfoModelMapper.updateByPrimaryKeySelective(getLoginUser());
				}
		
 
 
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			commonModel.setFlag(false);
			commonModel.setMessage("修改密码时错误");
		}
		
		
		return commonModel;
	}
 
	
 

 
}
