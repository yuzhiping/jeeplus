package com.jeeplus.weixin.api.core.req.model.kfaccount;


import com.jeeplus.weixin.api.core.annotation.ReqType;
import com.jeeplus.weixin.api.core.req.model.WeixinReqParam;

/**
 * 取多媒体文件
 * 
 * Created by yuzp17311 on 2016/8/22.
 * 
 */
@ReqType("kfaccountUpdate")
public class KfaccountUpdate extends WeixinReqParam {

	private String kf_account;
	
	private String nickname;
	
	private String password;

	public String getKf_account() {
		return kf_account;
	}

	public void setKf_account(String kf_account) {
		this.kf_account = kf_account;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	
}
