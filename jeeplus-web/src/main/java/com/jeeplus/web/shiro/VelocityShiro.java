package com.jeeplus.web.shiro;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;

/**
 * Shiro权限标签(Velocity版)
 * @author:yuzp17311
 * @version:v1.0
 * @date: 2016-12-11 17:41.
 */
public class VelocityShiro {

    /**

     * 是否拥有该权限

     * @param permission  权限标识

     * @return   true：是     false：否

     */
    public boolean hasPermission(String permission) {
        Subject subject = SecurityUtils.getSubject();
        return subject != null && subject.isPermitted(permission);
    }

}
