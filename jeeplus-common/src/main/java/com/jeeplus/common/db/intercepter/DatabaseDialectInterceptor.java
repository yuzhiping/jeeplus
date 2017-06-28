package com.jeeplus.common.db.intercepter;

import com.jeeplus.common.db.dialect.IDataBaseDialect;
import org.apache.ibatis.plugin.Interceptor;

/**
 * 带数据库方言的拦截器
 * @author:yuzp17311
 * @version:v1.0
 * @date: 2017-06-28 20:59.
 */
public abstract class DatabaseDialectInterceptor implements Interceptor {

    private IDataBaseDialect dataBaseDialect;

    public IDataBaseDialect getDataBaseDialect() {
        return dataBaseDialect;
    }

    public void setDataBaseDialect(IDataBaseDialect dataBaseDialect) {
        this.dataBaseDialect = dataBaseDialect;
    }
}
