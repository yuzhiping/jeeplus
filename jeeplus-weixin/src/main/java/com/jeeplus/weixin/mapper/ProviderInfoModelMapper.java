package com.jeeplus.weixin.mapper;


import com.jeeplus.weixin.entities.ProviderInfoModel;

public interface ProviderInfoModelMapper {
    int deleteByPrimaryKey(Integer recordid);

    int insert(ProviderInfoModel record);

    int insertSelective(ProviderInfoModel record);

    ProviderInfoModel selectByPrimaryKey(Integer recordid);

    int updateByPrimaryKeySelective(ProviderInfoModel record);

    int updateByPrimaryKey(ProviderInfoModel record);
}