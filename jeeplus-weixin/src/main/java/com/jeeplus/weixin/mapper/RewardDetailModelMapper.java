package com.jeeplus.weixin.mapper;


import com.jeeplus.weixin.common.pagination.Page;
import com.jeeplus.weixin.dto.RewardModel;
import com.jeeplus.weixin.entities.RewardDetailModel;

public interface RewardDetailModelMapper{
	
	RewardDetailModel selectByPrimaryKey(Integer recordid);

	int deleteByPrimaryKey(Integer recordid);
	
	int insert(RewardDetailModel record);
	
	int insertSelective(RewardDetailModel record);
	
	int updateByPrimaryKeySelective(RewardDetailModel record);
	
	int updateByPrimaryKey(RewardDetailModel record);
	
	RewardDetailModel selectByRewarduser(Integer rewarduser);
	
	int sumReward(RewardModel rewardModel);
	
	Page<RewardModel> selectByRewardUser(RewardDetailModel rewardDetailModel);
	
}