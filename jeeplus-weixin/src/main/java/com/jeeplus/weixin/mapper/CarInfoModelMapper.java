package com.jeeplus.weixin.mapper;



import com.jeeplus.weixin.entities.CarInfoModel;

import java.util.List;

public interface CarInfoModelMapper {
	
    int deleteByPrimaryKey(Integer recordid);

    int insert(CarInfoModel record);

    int insertSelective(CarInfoModel record);

    CarInfoModel selectByPrimaryKey(Integer recordid);

    int updateByPrimaryKeySelective(CarInfoModel record);

    int updateByPrimaryKey(CarInfoModel record);
    
    
    List<CarInfoModel> selectCarInfoList();
}