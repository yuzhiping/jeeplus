package com.jeeplus.weixin.entities;

import java.util.Date;

public class ProviderInfoModel {
    private Integer recordid;

    private Short providerid;

    private String providername;

    private String phoneno;

    private Byte providertype;

    private Byte deletestatus;

    private Date updatetime;

    public Integer getRecordid() {
        return recordid;
    }

    public void setRecordid(Integer recordid) {
        this.recordid = recordid;
    }

    public Short getProviderid() {
        return providerid;
    }

    public void setProviderid(Short providerid) {
        this.providerid = providerid;
    }

    public String getProvidername() {
        return providername;
    }

    public void setProvidername(String providername) {
        this.providername = providername;
    }

    public String getPhoneno() {
        return phoneno;
    }

    public void setPhoneno(String phoneno) {
        this.phoneno = phoneno;
    }

    public Byte getProvidertype() {
        return providertype;
    }

    public void setProvidertype(Byte providertype) {
        this.providertype = providertype;
    }

    public Byte getDeletestatus() {
        return deletestatus;
    }

    public void setDeletestatus(Byte deletestatus) {
        this.deletestatus = deletestatus;
    }

    public Date getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(Date updatetime) {
        this.updatetime = updatetime;
    }
}