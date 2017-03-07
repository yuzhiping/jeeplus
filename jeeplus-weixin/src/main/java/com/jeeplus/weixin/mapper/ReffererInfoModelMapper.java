package com.jeeplus.weixin.mapper;


import com.jeeplus.weixin.entities.ReffererInfoModel;

public interface ReffererInfoModelMapper {
    int deleteByPrimaryKey(Integer recordid);

    int insert(ReffererInfoModel record);

    int insertSelective(ReffererInfoModel record);

    ReffererInfoModel selectByPrimaryKey(Integer recordid);

    int updateByPrimaryKeySelective(ReffererInfoModel record);

    int updateByPrimaryKey(ReffererInfoModel record);
}