package com.jeeplus.common.aop;

import com.jeeplus.common.dto.PageDTO;
import org.apache.ibatis.executor.resultset.ResultSetHandler;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.plugin.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.Statement;
import java.util.Properties;

/**
 * Created by yuzp17311 on 2016/9/2.
 */
@Intercepts({
    @Signature(type = StatementHandler.class,method = "prepare" ,args = { Connection.class }),
        @Signature(type = ResultSetHandler.class, method = "handleResultSets", args = { Statement.class }) })
public class MysqlPageHelper implements Interceptor {

    private static final Logger logger = LoggerFactory
            .getLogger(MysqlPageHelper.class);

    public static final ThreadLocal<PageDTO> localPage = new ThreadLocal<PageDTO>();
    public Object intercept(Invocation invocation) throws Throwable {
        return null;
    }

    /**
     * 只拦截这两种类型的 <br>
     * StatementHandler <br>
     * ResultSetHandler
     *
     * @param target
     * @return
     */
    @Override
    public Object plugin(Object target) {
        if (target instanceof StatementHandler
                || target instanceof ResultSetHandler) {
            return Plugin.wrap(target, this);
        } else {
            return target;
        }
    }

    @Override
    public void setProperties(Properties properties) {

    }

}
