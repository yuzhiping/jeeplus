package com.jeeplus.common.db;

import com.jeeplus.common.db.dialect.IDataBaseDialect;
import com.jeeplus.common.db.exception.DataBaseDialectException;
import com.jeeplus.common.db.intercepter.DatabaseDialectInterceptor;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 * @author:yuzp17311
 * @version:v1.0
 * @date: 2017-06-28 20:37.
 */
public class SqlSessionFactoryBean extends org.mybatis.spring.SqlSessionFactoryBean {

    private static final Logger LOG = LoggerFactory.getLogger(SqlSessionFactoryBean.class);

    private IDataBaseDialect dataBaseDialect;

    public IDataBaseDialect getDataBaseDialect() {
        return dataBaseDialect;
    }

    public void setDataBaseDialect(IDataBaseDialect dataBaseDialect) {
        this.dataBaseDialect = dataBaseDialect;
    }

    protected SqlSessionFactory buildSqlSessionFactory() throws IOException {
        LOG.info("mybatis is starting..");
        //使用父类方法创建factory
        SqlSessionFactory factory = super.buildSqlSessionFactory();


        Configuration configuration = factory.getConfiguration();

        //设置方言
        if (dataBaseDialect == null) {
            throw new DataBaseDialectException(" not set dataBaseDialect. ");
        }
        configuration.setDatabaseId(dataBaseDialect.getDataBaseId());

        //向拦截器设置方言
        for (Interceptor interceptor : configuration.getInterceptors()) {
            if (interceptor instanceof DatabaseDialectInterceptor) {
                ((DatabaseDialectInterceptor) interceptor).setDataBaseDialect(getDataBaseDialect());
            }
        }

        LOG.info("mybatis ready ok. ");
        return factory;
    }

}
