package com.jeeplus.blog.common.mapper;

/**
 * @author:yuzp17311
 * @version:v1.0
 * @date: 2017-02-24 15:05.
 */
public interface BaseMapper<T> {
    void add(T obj);

    void delete(T obj);

    void update(T obj);

    T get(Object id);
}
