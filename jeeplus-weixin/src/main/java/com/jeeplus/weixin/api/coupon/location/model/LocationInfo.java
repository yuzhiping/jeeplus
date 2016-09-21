package com.jeeplus.weixin.api.coupon.location.model;


import com.jeeplus.weixin.api.core.annotation.ReqType;
import com.jeeplus.weixin.api.core.req.model.WeixinReqParam;

@ReqType("getLocationInfo")
public class LocationInfo extends WeixinReqParam {
	// 图片地址
	private String filePathName;

	public String getFilePathName() {
		return filePathName;
	}

	public void setFilePathName(String filePathName) {
		this.filePathName = filePathName;
	}


	
}
