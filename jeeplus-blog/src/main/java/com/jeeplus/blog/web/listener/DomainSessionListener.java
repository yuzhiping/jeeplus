package com.jeeplus.blog.web.listener;

import com.jeeplus.blog.common.enums.Variable;
import com.jeeplus.blog.common.service.CommonService;
import com.jeeplus.blog.common.service.impl.CommonServiceImpl;
import com.jeeplus.common.util.SpringUtils;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/**
 * session监听器
 * @author:yuzp17311
 * @version:v1.0
 * @date: 2017-05-24 10:33.
 */
public class DomainSessionListener implements HttpSessionListener {
    @Override
    public void sessionCreated(HttpSessionEvent httpSessionEvent) {

        CommonService commonService= SpringUtils.getBean(CommonServiceImpl.class);
        try {
            commonService.addCache(Variable.CACHE_CONTENT_SESSION.getKey(), httpSessionEvent.getSession().getId(), httpSessionEvent.getSession());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void sessionDestroyed(HttpSessionEvent httpSessionEvent) {

        CommonService commonService= SpringUtils.getBean(CommonServiceImpl.class);
        try {
            commonService.removeCache(Variable.CACHE_CONTENT_SESSION.getKey(), httpSessionEvent.getSession().getId()) ;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
