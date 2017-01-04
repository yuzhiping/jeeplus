package com.jeeplus.web.mapper;

import com.jeeplus.web.entities.SysUserEntity;

import java.util.List;
import java.util.Map;

/**
 * 系统用户
 * @author:yuzp17311
 * @version:v1.0
 * @date: 2016-12-12 9:45.
 */
public interface SysUserMapper extends BaseMapper<SysUserEntity> {

    /**
     * 查询用户的所有权限
     * @param userId  用户ID
     */
    List<String> queryAllPerms(Long userId);

    /**
     * 查询用户的所有菜单ID
     */
    List<Long> queryAllMenuId(Long userId);

    /**
     * 根据用户名，查询系统用户
     */
    SysUserEntity queryByUserName(String username);

    /**
     * 修改密码
     */
    int updatePassword(Map<String, Object> map);

}
