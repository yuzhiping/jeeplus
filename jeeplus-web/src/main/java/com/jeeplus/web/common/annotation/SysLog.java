package com.jeeplus.web.common.annotation;

import java.lang.annotation.*;

/**
 * 系统日志注解
 * @author:yuzp17311
 * @version:v1.0
 * @date: 2017-03-30 10:50.
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface SysLog {
    String value()default "";
}
