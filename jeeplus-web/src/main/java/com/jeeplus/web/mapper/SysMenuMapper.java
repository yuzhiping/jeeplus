package com.jeeplus.web.mapper;

import com.jeeplus.web.entities.SysMenuEntity;

import java.util.List;

/**
 * 菜单管理
 * @author:yuzp17311
 * @version:v1.0
 * @date: 2016-12-12 9:41.
 */
public interface SysMenuMapper extends BaseDao<SysMenuEntity> {

    /**
     * 根据父菜单，查询子菜单
     * @param parentId 父菜单ID
     */
    List<SysMenuEntity> queryListParentId(Long parentId);

    /**
     * 获取不包含按钮的菜单列表
     */
    List<SysMenuEntity> queryNotButtonList();

}
