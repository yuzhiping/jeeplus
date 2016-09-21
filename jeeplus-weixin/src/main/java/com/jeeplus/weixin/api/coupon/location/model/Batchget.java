package com.jeeplus.weixin.api.coupon.location.model;


import com.jeeplus.weixin.api.core.annotation.ReqType;
import com.jeeplus.weixin.api.core.req.model.WeixinReqParam;

@ReqType("getBatchget")
public class Batchget extends WeixinReqParam {
	//偏移量，0 开始
	private Integer offset ;
	//拉取数量
	private Integer count ;
	
	public Integer getOffset() {
		return offset;
	}
	public void setOffset(Integer offset) {
		this.offset = offset;
	}
	public Integer getCount() {
		return count;
	}
	public void setCount(Integer count) {
		this.count = count;
	}
	
	
	
}
