package com.jeeplus.web.common.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import org.springframework.web.context.ServletContextAware;

import javax.servlet.ServletContext;

/**
 * @author:yuzp17311
 * @version:v1.0
 * @date: 2017-02-14 13:57.
 */
@Component
public class StartUpListener implements ApplicationContextAware,ServletContextAware,
        InitializingBean,ApplicationListener<ContextRefreshedEvent> {
    private static final Logger logger= LoggerFactory.getLogger(StartUpListener.class);
    @Override
    public void afterPropertiesSet() throws Exception {
        logger.info("StartUpListenerTest.afterPropertiesSet ");
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        logger.info("1==>ApplicationContext.setApplicationContext");
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        logger.info("4.1 ==> ContextRefreshedEvent.onApplicationEvent");
        if(contextRefreshedEvent.getApplicationContext().getParent()==null)
            logger.info("4.2==>StartUpListenerTest.onApplicationEvent");
    }

    @Override
    public void setServletContext(ServletContext servletContext) {
        logger.info("2==>ServletContext.setServletContext");
    }
}
