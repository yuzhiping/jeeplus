package com.jeeplus.web.services.impl;

import com.jeeplus.web.entities.SysRoleEntity;
import com.jeeplus.web.mapper.SysRoleMapper;
import com.jeeplus.web.services.SysRoleMenuService;
import com.jeeplus.web.services.SysRoleService;
import com.jeeplus.web.services.SysUserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 角色
 * @author:yuzp17311
 * @version:v1.0
 * @date: 2016-12-12 13:59.
 */
@Service("sysRoleService")
public class SysRoleServiceImpl implements SysRoleService {
    @Autowired
    private SysRoleMapper sysRoleMapper;
    @Autowired
    private SysRoleMenuService sysRoleMenuService;

    @Override
    public SysRoleEntity queryObject(Long roleId) {
        return sysRoleMapper.queryObject(roleId);
    }

    @Override
    public List<SysRoleEntity> queryList(Map<String, Object> map) {
        return sysRoleMapper.queryList(map);
    }

    @Override
    public int queryTotal(Map<String, Object> map) {
        return sysRoleMapper.queryTotal(map);
    }

    @Override
    @Transactional
    public void save(SysRoleEntity role) {
        role.setCreateTime(new Date());
        sysRoleMapper.save(role);

        //保存角色与菜单关系
        sysRoleMenuService.saveOrUpdate(role.getRoleId(), role.getMenuIdList());
    }

    @Override
    @Transactional
    public void update(SysRoleEntity role) {
        sysRoleMapper.update(role);

        //更新角色与菜单关系
        sysRoleMenuService.saveOrUpdate(role.getRoleId(), role.getMenuIdList());
    }

    @Override
    @Transactional
    public void deleteBatch(Long[] roleIds) {
        sysRoleMapper.deleteBatch(roleIds);
    }
}
