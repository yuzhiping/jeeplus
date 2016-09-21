package com.jeeplus.weixin.api.core.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * 微信请求处理类型
 * Created by yuzp17311 on 2016/8/22.
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface ReqType {
     String value() default "";
}
