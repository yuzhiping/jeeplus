package com.jeeplus.common.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.ThrowsAdvice;

/**
 * 系统切面，目前主要用来记录service的执行时间
 * @author:yuzp17311
 * @version:v1.0
 * @date: 2017-06-30 14:42.
 */
@Aspect
public class ServiceAspect implements ThrowsAdvice {

    private static Logger logger = LoggerFactory.getLogger(ServiceAspect.class);

    private String buildString(JoinPoint joinPoint) {
        StringBuilder builder = new StringBuilder();
        builder.append("类名:");
        builder.append(joinPoint.getTarget().getClass().getName());
        builder.append(",");
        builder.append("方法名:");
        builder.append(joinPoint.getSignature().getName());
        return builder.toString();
    }


    public Object around(JoinPoint joinPoint) throws Throwable {
        Object result = null;
        if (logger.isInfoEnabled()) {
            long start = System.currentTimeMillis();
            result = ((ProceedingJoinPoint) joinPoint).proceed();
            long end = System.currentTimeMillis();
            long times = end - start;
            logger.info(buildString(joinPoint) + ",执行时间:" + times + "ms");
        } else {
            result = ((ProceedingJoinPoint) joinPoint).proceed();
        }
        return result;
    }

}
