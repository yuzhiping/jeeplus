package com.jeeplus.weixin.controller;

 

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.btkj.entity.CommonModel;
import cn.btkj.entity.UserInfoModel;
import cn.btkj.extendentity.ResetPwdModel;
import cn.btkj.service.PersonService;
import cn.btkj.utils.Constants;


 

/**
 * Created by lzh  
 */
@Controller
@RequestMapping("/person")
public class PersonController extends BaseController {

    private final String prefix = "person/";

    @Autowired
    private PersonService personService;

    
	  
    /**
    * 个人中心
    * @return
    */ 
   @RequestMapping("/myCenter")
   public String myCenter(UserInfoModel userInfoModel,Model model){
    
       
       return prefix+"myCenter";
   }
	   
		  
	   /**
	   * 个人资料
	   * @return
	   */ 
	  @RequestMapping("/personInfo")
	  public String personInfo(@RequestParam(value = "userid",required = false)Integer userid,Model model){
	   
		  personService.setPersonData(userid,model);
		  
	      return prefix+"personInfo";
	  }
	  
	    /**
	     * 修改个人信息
	     * @param myOrderModel
	     * @return
	     */
	    @RequestMapping("/uptPersonInfo")
	    @ResponseBody
	    public CommonModel uptPersonInfo(UserInfoModel  userInfoModel){

	        return  personService.uptPersonInfo(userInfoModel);
	    }
	    
	  
	  /**
	  * 推荐人资料
	  * @return
	  */
	 @RequestMapping("/refferInfo")
	 public String referrerInfo(UserInfoModel userInfoModel,Model model){
	  
		 personService.setPersonData(userInfoModel.getRecordid(),model);
		  
	     return prefix+"refferInfo";
	 }
	  
	  
	  /**
	  * 跳到修改密码界面
	  * @return
	  */
	 @RequestMapping("/updatePass")
	 public String updatePass(){
		  
	     return prefix+"updatePass";
	 }
	 
	 /**
     * 保存修改密码
     * @param myOrderModel
     * @return
     */
     @RequestMapping("/saveUpdatePass")
     @ResponseBody
	 public CommonModel saveUpdatePass(ResetPwdModel resetPwdModel,Model model) {
    	  return  personService.saveUpdatePass(resetPwdModel, model);
	 }
	 
	 
    /**
     * 修改推荐人信息
     * @param myOrderModel
     * @return
     */
     @RequestMapping("/uptrefferInfo")
     @ResponseBody
	 public CommonModel uptrefferInfo(UserInfoModel userInfoModel) {
    	  return  personService.uptrefferInfo(userInfoModel);
	 }
	 
	/**
	 * 登出
	 */
	@RequestMapping("/out")
	public String loginout(HttpSession session){
		// 清除会话session
		session.removeAttribute(Constants.USER_SESSION_LOGIN);
		session.invalidate();
		delCookie(request, response, "password");
		delCookie(request, response, "phoneNo");
		
		// 跳转登录页
		return "redirect:/front/login";
	}
}
