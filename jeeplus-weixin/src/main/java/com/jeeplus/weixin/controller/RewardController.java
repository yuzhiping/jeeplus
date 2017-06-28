package com.jeeplus.weixin.controller;

import com.jeeplus.weixin.common.pagination.PageInfo;
import com.jeeplus.weixin.dto.RewardModel;
import com.jeeplus.weixin.service.RewardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
@Controller
@RequestMapping("/reward")
public class RewardController extends BaseController{
	private final String prefix = "reward/";
	@Autowired
	private RewardService rewardService;
	
	/**
     * 奖励页面
     * @return
     */ 
    @RequestMapping("/reward")
    public String reward(RewardModel rewardModel, Model model){
    	
    	//UserInfoModel  user=(UserInfoModel) session.getAttribute("user_session_login");
    	
    	//rewardModel.setRewarduser(15/*user.getRecordid()*/);
    	
    	PageInfo pageInfo = rewardService.selectRewardInfoPageList(rewardModel);
    	
        rewardService.setRewardData(pageInfo,rewardModel,model);

        return prefix+"reward";
    }
    
    /**
     * 奖励加载更多
     * @return
     */ 
    @RequestMapping("/laodreward_more")
    @ResponseBody
    public PageInfo laodreward_more(RewardModel rewardModel){
    	
        PageInfo pageInfo = rewardService.selectRewardInfoPageList(rewardModel);
       
        return pageInfo;
    }
    
    @RequestMapping("/awardRule")
    public String awardRule(){

        return prefix+"awardRule";
    }
}