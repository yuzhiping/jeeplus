package com.jeeplus.weixin.dto;


import com.jeeplus.weixin.entities.BaseModel;

/**
 * 获取短信动态码m模型
 * @author lzh
 *
 */
public class VerifyCodeModel extends BaseModel {

	
	public String mobile;
	
	public String vrifyCode;
	
	public String ip;
	
	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getVrifyCode() {
		return vrifyCode;
	}

	public void setVrifyCode(String vrifyCode) {
		this.vrifyCode = vrifyCode;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}
 
	
	
	
}
