package com.jeeplus.blog.common.bean.convert;

/**
 * @author:yuzp17311
 * @version:v1.0
 * @date: 2017-02-23 16:29.
 */
public interface BeanConvert {

    <T> T po2vo(T obj) throws Exception;

}
