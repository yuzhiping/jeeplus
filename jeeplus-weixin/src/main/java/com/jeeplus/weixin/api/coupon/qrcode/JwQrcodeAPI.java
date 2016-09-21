package com.jeeplus.weixin.api.coupon.qrcode;

import com.jeeplus.weixin.api.core.exception.WexinReqException;
import com.jeeplus.weixin.api.core.req.WeiXinReqService;
import com.jeeplus.weixin.api.coupon.qrcode.model.Getticket;
import com.jeeplus.weixin.api.coupon.qrcode.model.GetticketRtn;
import com.jeeplus.weixin.api.coupon.qrcode.model.QrcodeInfo;
import com.jeeplus.weixin.api.coupon.qrcode.model.QrcodeRtnInfo;
import net.sf.json.JSONObject;




/**
 * 微信卡券 - 卡券投放
 * @author lihongxuan
 *
 */
public class JwQrcodeAPI {

	/**
	 * 创建二维码
	 * @throws WexinReqException 
	 */
	public static QrcodeRtnInfo doAddQrcode(String accesstoken, QrcodeInfo qrcodeInfo) throws WexinReqException {
		if (accesstoken != null) {
			qrcodeInfo.setAccess_token(accesstoken);
			JSONObject result = WeiXinReqService.getInstance().doWeinxinReqJson(qrcodeInfo);
			QrcodeRtnInfo qrcodeRtnInfo = (QrcodeRtnInfo)JSONObject.toBean(result, QrcodeRtnInfo.class);
			return qrcodeRtnInfo;
		}
		return null;
	}

	/**
	 * 获取api_ticket
	 */
	public static GetticketRtn doGetticket(String accesstoken)throws WexinReqException {
		if (accesstoken != null) {
			Getticket gk = new Getticket();
			gk.setAccess_token(accesstoken);
			JSONObject result = WeiXinReqService.getInstance().doWeinxinReqJson(gk);
			GetticketRtn getticketRtn = (GetticketRtn)JSONObject.toBean(result, GetticketRtn.class);
			return getticketRtn;
		}
		return null;
	}

}
 