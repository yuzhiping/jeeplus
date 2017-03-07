package com.jeeplus.weixin.mapper;


import com.jeeplus.weixin.dto.MyFoundModel;
import com.jeeplus.weixin.entities.UserAccountModel;

public interface UserAccountModelMapper {
    int deleteByPrimaryKey(Integer recordid);

    int insert(UserAccountModel record);

    int insertSelective(UserAccountModel record);

    UserAccountModel selectByUserid(Integer userid);

    int updateByPrimaryKeySelective(UserAccountModel record);

    int updateByPrimaryKey(UserAccountModel record);
    
    MyFoundModel selectmyFoundByUserid(Integer userid);
}