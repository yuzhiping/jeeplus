package com.jeeplus.weixin.api.core.req.model.user;


import com.jeeplus.weixin.api.core.annotation.ReqType;
import com.jeeplus.weixin.api.core.req.model.WeixinReqParam;

/**
 * 取多媒体文件
 * 
 * Created by yuzp17311 on 2016/8/22.
 * 
 */
@ReqType("userRemarkUpdate")
public class UserRemarkUpdate extends WeixinReqParam {

	private String openid;

	private String remark;

	public String getOpenid() {
		return openid;
	}

	public void setOpenid(String openid) {
		this.openid = openid;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

}
