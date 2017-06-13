package com.jeeplus.common.util;

import org.springframework.web.context.ContextLoader;

/**
 * @author:yuzp17311
 * @version:v1.0
 * @date: 2017-02-22 14:14.
 */
@SuppressWarnings("unchecked")
public class SpringUtils {

    public static <T> T getBean(Class<?> clazz){
        return (T) ContextLoader.getCurrentWebApplicationContext().getBean(clazz);
    }

    public static <T> T getBean(String id){
        return (T) ContextLoader.getCurrentWebApplicationContext().getBean(id);
    }

}
