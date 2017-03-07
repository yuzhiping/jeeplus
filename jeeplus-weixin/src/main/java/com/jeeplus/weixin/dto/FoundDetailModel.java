package com.jeeplus.weixin.dto;


import com.jeeplus.weixin.entities.UserAccountDetailModel;

public class FoundDetailModel  extends UserAccountDetailModel {

     private String accountopt_name;
 
     private String reason;
     
	 public String getAccountopt_name() {
		return accountopt_name;
	 }

	 public void setAccountopt_name(String accountopt_name) {
		this.accountopt_name = accountopt_name;
	 }

	 public String getReason() {
		return reason;
	 }

	 public void setReason(String reason) {
		this.reason = reason;
	 }
 
	 
	 
}
