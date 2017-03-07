package com.jeeplus.weixin.mapper;

 
import com.jeeplus.weixin.dto.OrderDetailModel;
import com.jeeplus.weixin.entities.CommonModel;
import com.jeeplus.weixin.entities.DictModel;
import com.jeeplus.weixin.entities.SQLAdapterModel;

import java.util.List;

public interface DictModelMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(DictModel record);

    int insertSelective(DictModel record);

    DictModel selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(DictModel record);

    int updateByPrimaryKey(DictModel record);
    
    List<DictModel>selectSelective(DictModel dictModel);
    
    List<CommonModel>selectBySql(SQLAdapterModel sql);
    
    List<OrderDetailModel> selectMyOrderList(OrderDetailModel orderDetailModel);
 
    int selectCount(SQLAdapterModel sql);
    
    String selectString(SQLAdapterModel sql);
}