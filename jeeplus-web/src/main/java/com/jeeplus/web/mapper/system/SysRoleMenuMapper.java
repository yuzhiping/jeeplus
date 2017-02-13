package com.jeeplus.web.mapper.system;

import com.jeeplus.web.entities.system.SysRoleMenuEntity;
import com.jeeplus.web.mapper.BaseMapper;

import java.util.List;

/**
 * 角色与菜单对应关系
 * @author:yuzp17311
 * @version:v1.0
 * @date: 2016-12-12 9:43.
 */
public interface SysRoleMenuMapper extends BaseMapper<SysRoleMenuEntity> {

    /**
     * 根据角色ID，获取菜单ID列表
     */
    List<Long> queryMenuIdList(Long roleId);

}
