package com.jeeplus.common.service;

import java.io.Serializable;

/**
 * Created by yuzp17311 on 2016/9/12.
 */
public interface SystemService {

    /**
     * 日志添加
     * @param LogContent 内容
     * @param loglevel 级别
     * @param operatetype 类型
     */
    void addLog(String LogContent, Short loglevel, Short operatetype);

    /**
     * 根据实体名称和主键获取实体
     *
     * @param <T>
     * @param entityName
     * @param id
     * @return
     */
    <T> T getEntity(Class entityName, Serializable id);

    /**
     * 根据实体名称和主键获取实体
     *
     * @param <T>
     * @param class1
     * @param id
     * @return
     */
    <T> T get(Class<T> class1, Serializable id);


}
