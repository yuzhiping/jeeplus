package com.jeeplus.weixin.dto;

/**
 * 重置密码模型 
 * @author lzh
 *
 */
public class ResetPwdModel {

	
	private String phoneno;
	private String dynamiccode;
	private String pwd;
	
	public String getPhoneno() {
		return phoneno;
	}
	public void setPhoneno(String phoneno) {
		this.phoneno = phoneno;
	}
	public String getDynamiccode() {
		return dynamiccode;
	}
	public void setDynamiccode(String dynamiccode) {
		this.dynamiccode = dynamiccode;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	
}
