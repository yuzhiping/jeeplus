package com.jeeplus.weixin.mapper;


import com.jeeplus.weixin.common.pagination.Page;
import com.jeeplus.weixin.dto.OrderDetailModel;
import com.jeeplus.weixin.entities.OrderInfoModel;

public interface OrderInfoModelMapper {
	
    int deleteByPrimaryKey(Integer recordid);

    int insert(OrderInfoModel record);

    int insertSelective(OrderInfoModel record);

    OrderDetailModel selectByPrimaryKey(Integer recordid);

    int updateByPrimaryKeySelective(OrderInfoModel record);

    int updateByPrimaryKey(OrderInfoModel record);
    
    Page<OrderDetailModel> selectMyOrderPageList(OrderDetailModel orderDetailModel);
}