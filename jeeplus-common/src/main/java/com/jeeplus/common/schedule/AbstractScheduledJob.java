package com.jeeplus.common.schedule;

import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.impl.triggers.CronTriggerImpl;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.expression.ParseException;
import org.springframework.util.Assert;

/**
 *  抽象类，把需要的实现的定时任务方法写成抽象方法，每个定时器去继承此抽象类
 * @author:yuzp17311
 * @version:v1.0
 * @date: 2016-09-22 9:51.
 */
public abstract class AbstractScheduledJob implements ApplicationContextAware {

    private static ApplicationContext applicationContext;
    protected String schedulerFactory;
    protected String triggerKey;


    @Override
    public void setApplicationContext(ApplicationContext appCtx) throws BeansException {
        applicationContext = appCtx;
    }

    public void setSchedulerFactory(String schedulerFactory) {
        Assert.notNull(schedulerFactory, "Scheduler factory can not be null");
        this.schedulerFactory = schedulerFactory;
    }

    public void setTriggerKey(String triggerKey) {
        Assert.notNull(triggerKey, "Trigger key can not be null");
        this.triggerKey = triggerKey;
    }

    public void resetScheduledJob(String cronExpression) throws SchedulerException, ParseException, java.text.ParseException {
        Scheduler schedulerFactory = (Scheduler) getObject(this.schedulerFactory);
        CronTriggerImpl cronTrigger = (CronTriggerImpl) getObject(this.triggerKey);
        String originCronExpression = cronTrigger.getCronExpression();

        if (!originCronExpression.equalsIgnoreCase(cronExpression)) {
            cronTrigger.setCronExpression(cronExpression);
            schedulerFactory.rescheduleJob(cronTrigger.getKey(), cronTrigger);
        }
    }

    public abstract void doScheduledJob();

    protected Object getObject(String name) {
        return applicationContext.getBean(name);
    }

}
