package com.jeeplus.weixin.api.core.req.model.user;


import com.jeeplus.weixin.api.core.annotation.ReqType;
import com.jeeplus.weixin.api.core.req.model.WeixinReqParam;

/**
 * 
 * 
 * Created by yuzp17311 on 2016/8/22.
 * 
 */
@ReqType("groupMembersUpdate")
public class GroupMembersUpdate extends WeixinReqParam {
	
	private String openid;
	
	private String to_groupid;

	public String getOpenid() {
		return openid;
	}

	public void setOpenid(String openid) {
		this.openid = openid;
	}

	public String getTo_groupid() {
		return to_groupid;
	}

	public void setTo_groupid(String to_groupid) {
		this.to_groupid = to_groupid;
	}

	
}
