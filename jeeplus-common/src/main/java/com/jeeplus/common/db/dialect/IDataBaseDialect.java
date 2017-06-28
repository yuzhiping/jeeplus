package com.jeeplus.common.db.dialect;

/**
 * 数据库方言
 * @author:yuzp17311
 * @version:v1.0
 * @date: 2017-06-28 20:40.
 */
public interface IDataBaseDialect {

    /**
     * 数据库标识 mysql oracle db2 sqlserver...
     * <br>标识必须用小写，无空格</br>
     * @return
     */
    String getDataBaseId();

}
