package com.jeeplus.weixin.mapper;


import com.jeeplus.weixin.common.pagination.Page;
import com.jeeplus.weixin.entities.CitiesModel;

public interface CitiesModelMapper {
	Page<CitiesModel> selectCityName();

	int deleteByPrimaryKey(String id);

    int insert(CitiesModel record);

    int insertSelective(CitiesModel record);

    CitiesModel selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(CitiesModel record);

    int updateByPrimaryKey(CitiesModel record);
    
    CitiesModel selectByCityName(CitiesModel citiesModel);
}