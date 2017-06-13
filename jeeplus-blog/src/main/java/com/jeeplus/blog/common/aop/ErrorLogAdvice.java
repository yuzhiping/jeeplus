package com.jeeplus.blog.common.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;

/**
 * @author:yuzp17311
 * @version:v1.0
 * @date: 2017-05-24 19:31.
 */
public class ErrorLogAdvice {

    public void before(){
        System.out.println("@before");
    }
    /**
     * 目标方法正常完成后，会被调用
     * @param args 目录方法的返回值
     */
    public void AfterReturning(Object args){
        System.out.println("@AfterReturning 获取目录方法返回值："+args);
        System.out.println("");
    }

    public void AfterThrowing(JoinPoint joinPoint, Throwable ex){

        System.out.println("目标方法抛出的异常@AfterThrowing："+ex);
    }
    /**
     * 与@AfterReturning的区别
     * 不管方法是否正常结束它都会调用
     */
    public void after(){
        System.out.println("@after");
    }
    /**
     * 可以决定方法在何时执行，甚至可以完全阻止目标方法执行
     * @throws Throwable
     */
    public Object around(ProceedingJoinPoint jp) throws Throwable{
        System.out.println(String.format("@Around:参数：%s | 目标%s  | getthis:%s", jp.getArgs().toString(),jp.getTarget().getClass().getName(),jp.getThis()));
        Object o =jp.proceed();
        //jp.proceed(xxx); 还可以改变参数
        return o;
    }

}
