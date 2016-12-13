package com.jeeplus.web.controller;

import com.jeeplus.web.entities.SysUserEntity;
import com.jeeplus.web.utils.ShiroUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Controller公共组件
 * @author:yuzp17311
 * @version:v1.0
 * @date: 2016-12-12 14:34.
 */
public abstract class AbstractController {
    protected Logger logger = LoggerFactory.getLogger(getClass());

    protected SysUserEntity getUser() {
        return ShiroUtils.getUserEntity();
    }

    protected Long getUserId() {
        return getUser().getUserId();
    }
}
