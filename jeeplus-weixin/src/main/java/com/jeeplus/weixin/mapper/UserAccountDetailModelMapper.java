package com.jeeplus.weixin.mapper;

import com.jeeplus.weixin.dto.FoundDetailModel;
import com.jeeplus.weixin.entities.UserAccountDetailModel;

import java.util.List;



public interface UserAccountDetailModelMapper {
    int deleteByPrimaryKey(Integer recordid);

    int insert(UserAccountDetailModel record);

    int insertSelective(UserAccountDetailModel record);

    UserAccountDetailModel selectByPrimaryKey(Integer recordid);

    int updateByPrimaryKeySelective(UserAccountDetailModel record);

    int updateByPrimaryKey(UserAccountDetailModel record);
    
    List<FoundDetailModel>  selectListByUserid(Integer userid);
}