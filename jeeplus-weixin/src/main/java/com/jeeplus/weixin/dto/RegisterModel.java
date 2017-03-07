package com.jeeplus.weixin.dto;


import com.jeeplus.weixin.entities.UserInfoModel;

public class RegisterModel extends UserInfoModel {

	
	private String refereePhone;

	public String getRefereePhone() {
		return refereePhone;
	}

	public void setRefereePhone(String refereePhone) {
		this.refereePhone = refereePhone;
	}

 
}
