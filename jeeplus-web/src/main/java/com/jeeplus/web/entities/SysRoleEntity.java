package com.jeeplus.web.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 角色
 * @author:yuzp17311
 * @version:v1.0
 * @date: 2016-12-11 16:47.
 */
public class SysRoleEntity implements Serializable {

    /**
     * 角色ID
     */
    private Long roleId;

    /**
     * 角色名称
     */
    private String roleName;

    /**
     * 备注
     */
    private String remark;

    private List<Long> menuIdList;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 设置：
     * @param roleId
     */
    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    /**
     * 获取：
     * @return Long
     */
    public Long getRoleId() {
        return roleId;
    }

    /**
     * 设置：角色名称
     * @param roleName 角色名称
     */
    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    /**
     * 获取：角色名称
     * @return String
     */
    public String getRoleName() {
        return roleName;
    }

    /**
     * 设置：备注
     * @param remark 备注
     */
    public void setRemark(String remark) {
        this.remark = remark;
    }

    /**
     * 获取：备注
     * @return String
     */
    public String getRemark() {
        return remark;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public List<Long> getMenuIdList() {
        return menuIdList;
    }

    public void setMenuIdList(List<Long> menuIdList) {
        this.menuIdList = menuIdList;
    }

}
