package com.jeeplus.web.entities;

/**
 * 系统配置信息
 * @author:yuzp17311
 * @version:v1.0
 * @date: 2016-12-11 16:45.
 */
public class SysConfigEntity {
    private Long id;
    private String key;
    private String value;
    private String remark;

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getKey() {
        return key;
    }
    public void setKey(String key) {
        this.key = key;
    }
    public String getValue() {
        return value;
    }
    public void setValue(String value) {
        this.value = value;
    }
    public String getRemark() {
        return remark;
    }
    public void setRemark(String remark) {
        this.remark = remark;
    }
}
