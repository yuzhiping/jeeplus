package com.jeeplus.weixin.mapper;


import com.jeeplus.weixin.entities.OrderHistoryModel;

public interface OrderHistoryModelMapper {
    int deleteByPrimaryKey(Integer recordid);

    int insert(OrderHistoryModel record);

    int insertSelective(OrderHistoryModel record);

    OrderHistoryModel selectByPrimaryKey(Integer recordid);

    int updateByPrimaryKeySelective(OrderHistoryModel record);

    int updateByPrimaryKey(OrderHistoryModel record);
}