package com.jeeplus.weixin.services;

import javax.servlet.http.HttpSession;

import com.jeeplus.weixin.entities.UserInfoModel;
import com.jeeplus.weixin.mapper.*;
import com.jeeplus.weixin.utils.Constants;
import com.jeeplus.weixin.utils.DictUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;



public class BaseService {
 
	
    protected String NULL_AVATAR = "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";
	    
    @Autowired
    protected DataSourceTransactionManager transactionManager;  
    
	@Autowired
	protected  HttpSession session;
	
    @Autowired
    protected CarInfoModelMapper carInfoModelMapper;
 
 
    @Autowired
    protected CitiesModelMapper citiesModelMapper;
     
    @Autowired
    protected OrderInfoModelMapper orderInfoModelMapper;
	
	
    @Autowired
    protected OrderHistoryModelMapper orderHistoryModelMapper;
    
    
    @Autowired
    protected SupplyCarModelMapper supplyCarModelMapper;
    
    @Autowired
    protected UserAccountModelMapper   userAccountModelMapper;
    
    @Autowired
    protected UserAccountDetailModelMapper   userAccountDetailModelMapper;
    
    @Autowired
    protected UserInfoModelMapper   userInfoModelMapper;
    
    
    @Autowired
    protected   DictModelMapper dictModelMapper;
    
    @Autowired
	protected RewardDetailModelMapper rewardDetailModelMapper;
    
    @Autowired
    protected   ReffererInfoModelMapper reffererInfoModelMapper;
    
    @Autowired
	protected DictUtil dictUtil;
    
	public UserInfoModel getLoginUser() {
	 
		UserInfoModel userInfoModel=(UserInfoModel) session.getAttribute(Constants.USER_SESSION_LOGIN);
		
		if(userInfoModel==null){
			userInfoModel =	new UserInfoModel();
		}
		
		return userInfoModel;
	}

}
