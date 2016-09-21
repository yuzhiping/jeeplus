package com.jeeplus.weixin.api.wxaccount.model;

/**
 * 微信二维码参数
 * Created by yuzp17311 on 2016/8/23.
 */
public class WxQrcode {

	private String ticket;//获取的二维码ticket，凭借此ticket可以在有效时间内换取二维码
	
	private String expire_seconds; //该二维码有效时间，以秒为单位。 最大不超过2592000（即30天）
	
	private String url; //二维码图片解析后的地址，开发者可根据该地址自行生成需要的二维码图片

	public String getTicket() {
		return ticket;
	}

	public void setTicket(String ticket) {
		this.ticket = ticket;
	}

	public String getExpire_seconds() {
		return expire_seconds;
	}

	public void setExpire_seconds(String expire_seconds) {
		this.expire_seconds = expire_seconds;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
	
}
