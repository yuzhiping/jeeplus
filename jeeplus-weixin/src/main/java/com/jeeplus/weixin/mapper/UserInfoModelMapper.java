package com.jeeplus.weixin.mapper;


import com.jeeplus.weixin.entities.UserInfoModel;

public interface UserInfoModelMapper {
	
    int deleteByPrimaryKey(Integer recordid);

    int insert(UserInfoModel record);

    int insertSelective(UserInfoModel record);

    UserInfoModel selectByPrimaryKey(Integer recordid);

    int updateByPrimaryKeySelective(UserInfoModel record);

    int updateByPrimaryKeyWithBLOBs(UserInfoModel record);

    int updateByPrimaryKey(UserInfoModel record);
    
    UserInfoModel selectByPhoneNo(String phoneNo);

    UserInfoModel selectByOpenid(String openid);
    
    
}