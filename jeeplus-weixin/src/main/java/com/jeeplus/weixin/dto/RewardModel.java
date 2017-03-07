package com.jeeplus.weixin.dto;

import com.jeeplus.weixin.entities.RewardDetailModel;

import java.util.Date;


public class RewardModel extends RewardDetailModel {

	private String phoneNo;

	private String rewardfromuserphone;

	private Integer reward;

	private String rewarddesc;

	private Date updatetime;
	
	private Integer total; //奖励总计
	
	public Integer getTotal(){
		return total;
	}
	
	public void setTotal(Integer total){
		this.total = total;
	}

	public String getPhoneNo() {
		return phoneNo;
	}

	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}

 

	public String getRewardfromuserphone() {
		return rewardfromuserphone;
	}

	public void setRewardfromuserphone(String rewardfromuserphone) {
		this.rewardfromuserphone = rewardfromuserphone;
	}

	public Integer getReward() {
		return reward;
	}

	public void setReward(Integer reward) {
		this.reward = reward;
	}

	public String getRewarddesc() {
		return rewarddesc;
	}

	public void setRewarddesc(String rewarddesc) {
		this.rewarddesc = rewarddesc;
	}

	public Date getUpdatetime() {
		return updatetime;
	}

	public void setUpdatetime(Date updatetime) {
		this.updatetime = updatetime;
	}
}
