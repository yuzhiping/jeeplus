package com.jeeplus.admin.common.listener;

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
 * @date: 2016-10-28 15:02.
 */
@Component
public class StartupListener implements ApplicationContextAware,ServletContextAware,InitializingBean,ApplicationListener<ContextRefreshedEvent> {

    private static final Logger logger= LoggerFactory.getLogger(StartupListener.class);

    @Override
    public void afterPropertiesSet() throws Exception {
        logger.info("3 => StartupListener.afterPropertiesSet");
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {

        logger.info("1=>StartupListener.setApplicationContext");
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        logger.info("4.1 => MyApplicationListener.onApplicationEvent");
        if (contextRefreshedEvent.getApplicationContext().getParent() == null) {
            logger.info("4.2 => MyApplicationListener.onApplicationEvent");
        }
    }

    @Override
    public void setServletContext(ServletContext servletContext) {
        logger.info("2 => StartupListener.setServletContext");
    }
}
