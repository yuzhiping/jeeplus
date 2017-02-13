package com.jeeplus.web.services.system;

import java.util.List;

/**
 * 角色与菜单对应关系
 * @author:yuzp17311
 * @version:v1.0
 * @date: 2016-12-11 17:32.
 */
public interface SysRoleMenuService {

    void saveOrUpdate(Long roleId, List<Long> menuIdList);

    /**
     * 根据角色ID，获取菜单ID列表
     */
    List<Long> queryMenuIdList(Long roleId);

}
