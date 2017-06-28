package com.jeeplus.common.db.dialect;

/**
 * oracle 数据库方言
 * @author:yuzp17311
 * @version:v1.0
 * @date: 2017-06-28 20:46.
 */
public class OracleDialect implements IDataBaseDialect {
    /**
     * 数据库标识 mysql oracle db2 sqlserver...
     * <br>标识必须用小写，无空格</br>
     *
     * @return
     */
    @Override
    public String getDataBaseId() {
        return "oracle";
    }
}
