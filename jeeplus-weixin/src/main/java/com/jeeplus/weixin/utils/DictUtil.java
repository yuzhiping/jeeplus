package com.jeeplus.weixin.utils;

import com.jeeplus.weixin.entities.CommonModel;
import com.jeeplus.weixin.entities.DictModel;
import com.jeeplus.weixin.entities.SQLAdapterModel;
import com.jeeplus.weixin.mapper.DictModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 余智平 on 2017/3/7.
 */
@Service
public class DictUtil {

    @Autowired
    public DictModelMapper dictModelMapper;

    public List<CommonModel> getDictListBycategory(String category){

        List<CommonModel> list = null;

        int type=dictModelMapper.selectCount(new SQLAdapterModel("select distinct type from tmk_dict where category='"+category+"'"));


        DictModel dictModel =new DictModel();

        if(type==1){
            dictModel.setType(1);
            dictModel.setCategory(category);
            List<DictModel> list2=dictModelMapper.selectSelective(dictModel);

            if(list2!=null&&list2.size()==1){
                dictModel=list2.get(0);
            }
            SQLAdapterModel sQLAdapterModel=new SQLAdapterModel(dictModel.getSqls());

            list=dictModelMapper.selectBySql(sQLAdapterModel);


        }else if(type==0){

            dictModel.setType(0);
            dictModel.setCategory(category);
            List<DictModel> list2=dictModelMapper.selectSelective(dictModel);

            list=new ArrayList<>();
            for (int i = 0; i < list2.size(); i++) {
                CommonModel commonModel=	new CommonModel();
                commonModel.setValue(list2.get(i).getValue());
                commonModel.setText(list2.get(i).getName());
                list.add(commonModel);
            }

        }



        return list;

    }

    public  String getDictNameByValue(String category,Object value){



        SQLAdapterModel sql=new SQLAdapterModel("select distinct type from tmk_dict where category='"+category+"'");

        sql.setSql("select distinct type from tmk_dict where category='"+category+"'");
        int type=dictModelMapper.selectCount(sql);


        DictModel dictModel =new DictModel();

        if(type==0){
            sql.setSql("select name from tmk_dict where category='"+category+"' and value='"+value+"'");

        }else{
            dictModel.setType(1);
            dictModel.setCategory(category);
            List<DictModel> list2=dictModelMapper.selectSelective(dictModel);

            if(list2!=null&&list2.size()==1){
                dictModel=list2.get(0);
            }
            sql.setSql("select text from ("+dictModel.getSqls()+") a where value='"+value+"'");
        }

        return  dictModelMapper.selectString(sql);
    }

}
