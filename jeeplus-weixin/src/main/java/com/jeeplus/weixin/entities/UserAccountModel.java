package com.jeeplus.weixin.entities;

import java.util.Date;

public class UserAccountModel {
    private Integer recordid;

    private Integer accountuser;

    private Integer deposit;

    private Byte depositfrozenstatus;

    private Integer reward;

    private Integer totalamount;

    private Date updatetime;

    private String  reason;
    
    private Date checktime;
   
    private Integer checkerid;
    
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

    public Integer getDeposit() {
        return deposit;
    }

    public void setDeposit(Integer deposit) {
        this.deposit = deposit;
    }

    public Byte getDepositfrozenstatus() {
        return depositfrozenstatus;
    }

    public void setDepositfrozenstatus(Byte depositfrozenstatus) {
        this.depositfrozenstatus = depositfrozenstatus;
    }

    public Integer getReward() {
        return reward;
    }

    public void setReward(Integer reward) {
        this.reward = reward;
    }

    public Integer getTotalamount() {
        return totalamount;
    }

    public void setTotalamount(Integer totalamount) {
        this.totalamount = totalamount;
    }

    public Date getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(Date updatetime) {
        this.updatetime = updatetime;
    }

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public Date getChecktime() {
		return checktime;
	}

	public void setChecktime(Date checktime) {
		this.checktime = checktime;
	}

	public Integer getCheckerid() {
		return checkerid;
	}

	public void setCheckerid(Integer checkerid) {
		this.checkerid = checkerid;
	}
    
    
}