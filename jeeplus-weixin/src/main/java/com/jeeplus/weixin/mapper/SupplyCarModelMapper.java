package com.jeeplus.weixin.mapper;


import com.jeeplus.weixin.common.pagination.Page;
import com.jeeplus.weixin.dto.IndexModel;
import com.jeeplus.weixin.entities.SupplyCarModel;

public interface SupplyCarModelMapper {
    int deleteByPrimaryKey(Integer recordid);

    int insert(SupplyCarModel record);

    int insertSelective(SupplyCarModel record);

    IndexModel selectByPrimaryKey(Integer recordid);

    int updateByPrimaryKeySelective(SupplyCarModel record);

    int updateByPrimaryKey(SupplyCarModel record);
    
    
    Page<IndexModel> selectSupplyCarList(IndexModel indexModel);
}