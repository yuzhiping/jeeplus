package com.jeeplus.web.service.system;

import com.jeeplus.web.entities.system.SysRoleEntity;

import java.util.List;
import java.util.Map;

/**
 * 角色
 * @author:yuzp17311
 * @version:v1.0
 * @date: 2016-12-11 17:33.
 */
public interface SysRoleService {

    SysRoleEntity queryObject(Long roleId);

    List<SysRoleEntity> queryList(Map<String, Object> map);

    int queryTotal(Map<String, Object> map);

    void save(SysRoleEntity role);

    void update(SysRoleEntity role);

    void deleteBatch(Long[] roleIds);

}
