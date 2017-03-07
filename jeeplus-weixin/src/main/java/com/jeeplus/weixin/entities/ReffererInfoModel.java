package com.jeeplus.weixin.entities;

import java.util.Date;

public class ReffererInfoModel {
	
    private Integer recordid;

    private String phoneno;

    private String refferno;

    private String refferinfo;

    private Date updatetime;

    public Integer getRecordid() {
        return recordid;
    }

    public void setRecordid(Integer recordid) {
        this.recordid = recordid;
    }

    public String getPhoneno() {
        return phoneno;
    }

    public void setPhoneno(String phoneno) {
        this.phoneno = phoneno;
    }

    public String getRefferno() {
        return refferno;
    }

    public void setRefferno(String refferno) {
        this.refferno = refferno;
    }

    public String getRefferinfo() {
        return refferinfo;
    }

    public void setRefferinfo(String refferinfo) {
        this.refferinfo = refferinfo;
    }

    public Date getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(Date updatetime) {
        this.updatetime = updatetime;
    }
}