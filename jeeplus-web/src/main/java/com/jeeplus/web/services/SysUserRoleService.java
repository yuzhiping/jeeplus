package com.jeeplus.web.services;

import java.util.List;

/**
 * 用户与角色对应关系
 * @author:yuzp17311
 * @version:v1.0
 * @date: 2016-12-11 17:34.
 */
public interface SysUserRoleService {

    void saveOrUpdate(Long userId, List<Long> roleIdList);

    /**
     * 根据用户ID，获取角色ID列表
     */
    List<Long> queryRoleIdList(Long userId);

    void delete(Long userId);

}
