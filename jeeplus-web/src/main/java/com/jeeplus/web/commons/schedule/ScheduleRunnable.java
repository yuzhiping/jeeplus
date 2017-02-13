package com.jeeplus.web.commons.schedule;

import com.jeeplus.web.commons.exception.RRException;
import com.jeeplus.web.util.SpringContextUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Method;

/**
 * 执行定时任务
 * @author:yuzp17311
 * @version:v1.0
 * @date: 2016-12-11 17:07.
 */
public class ScheduleRunnable implements Runnable {

    private Object target;
    private Method method;
    private String params;

    public ScheduleRunnable(String beanName, String methodName, String params)
            throws NoSuchMethodException,SecurityException{
        this.target= SpringContextUtils.getBean(beanName);
        this.params=params;
        if(StringUtils.isNotBlank(params)){
            this.method=target.getClass().getDeclaredMethod(methodName,String.class);
        }else{
            this.method=target.getClass().getDeclaredMethod(methodName);
        }
    }


    /**
     * When an object implementing interface <code>Runnable</code> is used
     * to create a thread, starting the thread causes the object's
     * <code>run</code> method to be called in that separately executing
     * thread.
     * <p>
     * The general contract of the method <code>run</code> is that it may
     * take any action whatsoever.
     *
     * @see Thread#run()
     */
    @Override
    public void run() {
        try {
            ReflectionUtils.makeAccessible(method);
            if(StringUtils.isNotBlank(params)){
                method.invoke(target, params);
            }else{
                method.invoke(target);
            }
        }catch (Exception e){
            throw new RRException("执行定时任务失败",e);
        }

    }
}
