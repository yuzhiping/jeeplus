package com.jeeplus.web.service.system.impl;

import com.jeeplus.web.mapper.system.SysUserRoleMapper;
import com.jeeplus.web.service.system.SysUserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author:yuzp17311
 * @version:v1.0
 * @date: 2016-12-12 14:02.
 */
@Service("sysUserRoleService")
public class SysUserRoleServiceImpl implements SysUserRoleService {
    @Autowired
    private SysUserRoleMapper sysUserRoleMapper;

    @Override
    public void saveOrUpdate(Long userId, List<Long> roleIdList) {
        if(roleIdList.size() == 0){
            return ;
        }

        //先删除用户与角色关系
        sysUserRoleMapper.delete(userId);

        //保存用户与角色关系
        Map<String, Object> map = new HashMap<>();
        map.put("userId", userId);
        map.put("roleIdList", roleIdList);
        sysUserRoleMapper.save(map);
    }

    @Override
    public List<Long> queryRoleIdList(Long userId) {
        return sysUserRoleMapper.queryRoleIdList(userId);
    }

    @Override
    public void delete(Long userId) {
        sysUserRoleMapper.delete(userId);
    }
}
