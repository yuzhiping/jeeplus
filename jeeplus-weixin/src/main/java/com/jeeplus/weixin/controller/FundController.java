package com.jeeplus.weixin.controller;

 

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.btkj.entity.CommonModel;
import cn.btkj.entity.UserAccountDetailModel;
import cn.btkj.entity.UserInfoModel;
import cn.btkj.service.FundService;
 


 

/**
 * Created by lzh  
 */
@Controller
@RequestMapping("/fund")
public class FundController extends BaseController {

    private final String prefix = "fund/";

    @Autowired
    private FundService fundService;

    
	  
	    /**
	    * 我的款项首页
	    * @return
	    */ 
	   @RequestMapping("/myFund")
	   public String myFund(@RequestParam(value = "orderuser",required = false)Integer orderuser,Model model){
	    
		   fundService.setMyFundData(orderuser,model);
		   
	       return prefix+"myFund";
	   }
	   
	    /**
	     * 保存确认预付款项
	     * @param UserAccountDetailModel
	     * @return
	     */
	    @RequestMapping("/savePrePayment")
	    @ResponseBody
	    public CommonModel savePrePayment(UserAccountDetailModel userAccountDetailModel){
	 
	        return  fundService.savePrePayment(userAccountDetailModel);
	    }
	   
	   /**
	   * 我的款项详情
	   * @return
	   */ 
	  @RequestMapping("/fundDetails")
	  public String fundDetails(@RequestParam(value = "accountuser",required = false)Integer accountuser,Model model){
	   
		  fundService.setFundDetailsData(accountuser,model);
		  
	      return prefix+"fundDetails";
	  }
	    
	  /**
	  * 跳到余额提现界面
	  * @return
	  */ 
	 @RequestMapping("/fundPropose")
	 public String fundPropose(UserInfoModel userInfoModel,Model model){
	  
	     
		 fundService.fundPropose(userInfoModel,model);
		 
	     return prefix+"fundPropose";
	 }
	 
	    
	  /**
	  * 余额提现
	  * @return
	  */ 
	 @RequestMapping("/withDrawals")
	 @ResponseBody
	 public CommonModel withDrawals(UserAccountDetailModel userAccountDetailModel,Model model){
	  
	     
		   return  fundService.withDrawals(userAccountDetailModel);
	 }
	 
	 
	 @RequestMapping("/fundPropose-explain")
	 public String fundProposeExplain(){
 
		 
	     return prefix+"fundPropose-explain";
	 }
	 
	 
}
