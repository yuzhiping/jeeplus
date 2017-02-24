package com.jeeplus.blog.common.service;

/**
 * @author:yuzp17311
 * @version:v1.0
 * @date: 2017-02-24 14:21.
 */
public interface CommonService {

    /**
     * 指定的缓存容器是否包含指定的key
     * @param cacheName ehcache配置的name
     * @param key key
     * @return
     * @
     */
    boolean cacheContrainKey(String cacheName, String key);


    /**
     * 向指定的缓存容器存放对象
     * @param cacheName
     * @param key
     * @param value
     * @
     */
    void addCache(String cacheName, String key, Object value);

    /**
     * 移除某一项
     * @param cacheName
     * @param key
     * @
     */
    void removeCache(String cacheName, String key);


    /**
     * 得到缓存
     * @param cacheName
     * @param key
     * @return
     * @
     */
    Object getCache(String cacheName, String key);


    /**
     * 记录http访问日志
     * @param url
     * @param method
     * @param ip
     * @param agent
     * @param referer
     * @param runmills
     * @param blog
     * @
     */
    void addHttpRequestLog(String url, String method, String ip, String agent, String referer, int runmills, String blog);



    /**
     *  得到一个实体
     * @param clazz
     * @param id
     * @return
     * @
     */
    Object get(Class<?> clazz, Object id) ;
    
}
