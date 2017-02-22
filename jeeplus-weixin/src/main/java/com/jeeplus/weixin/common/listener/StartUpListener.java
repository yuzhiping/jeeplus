package com.jeeplus.weixin.common.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * 应用启动时，检测access_token是否过期，如过期，从新获取access_token并更新
 * @author:yuzp17311
 * @version:v1.0
 * @date: 2017-02-14 13:43.
 */
public class StartUpListener implements ApplicationContextAware {

    private static final Logger logger= LoggerFactory.getLogger(StartUpListener.class);

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {

    }
}
