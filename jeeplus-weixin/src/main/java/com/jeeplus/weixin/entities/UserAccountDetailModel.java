package com.jeeplus.weixin.entities;

import java.util.Date;

public class UserAccountDetailModel {
	
    private Integer recordid;

    private Integer accountuser;

    private Byte accountopt;

    private Integer optamount;

    private Date updatetime;

    private String alipayaccount;
    
    private String realname;
    
    private String  wxopenid;
    
    public Integer getRecordid() {
        return recordid;
    }

    public void setRecordid(Integer recordid) {
        this.recordid = recordid;
    }

    public Integer getAccountuser() {
        return accountuser;
    }

    public void setAccountuser(Integer accountuser) {
        this.accountuser = accountuser;
    }

    public Byte getAccountopt() {
        return accountopt;
    }

    public void setAccountopt(Byte accountopt) {
        this.accountopt = accountopt;
    }

    public Integer getOptamount() {
        return optamount;
    }

    public void setOptamount(Integer optamount) {
        this.optamount = optamount;
    }

    public Date getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(Date updatetime) {
        this.updatetime = updatetime;
    }

	public String getAlipayaccount() {
		return alipayaccount;
	}

	public void setAlipayaccount(String alipayaccount) {
		this.alipayaccount = alipayaccount;
	}

	public String getRealname() {
		return realname;
	}

	public void setRealname(String realname) {
		this.realname = realname;
	}

	public String getWxopenid() {
		return wxopenid;
	}

	public void setWxopenid(String wxopenid) {
		this.wxopenid = wxopenid;
	}

 
    
}