package com.jeeplus.common.util;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;

/**
 * @author:yuzp17311
 * @version:v1.0
 * @date: 2017-06-30 14:25.
 */
public class ServiceLocator implements BeanFactoryAware {

    private static BeanFactory beanFactory;

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        this.beanFactory = beanFactory;
    }

    public static BeanFactory getBeanFactory(){
        return beanFactory;
    }
}
