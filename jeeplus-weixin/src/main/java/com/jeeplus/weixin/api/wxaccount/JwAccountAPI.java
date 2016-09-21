package com.jeeplus.weixin.api.wxaccount;

import net.sf.json.JSONObject;

import com.jeeplus.weixin.api.core.exception.WexinReqException;
import com.jeeplus.weixin.api.core.req.WeiXinReqService;
import com.jeeplus.weixin.api.core.req.model.account.QrcodeActionInfo;
import com.jeeplus.weixin.api.core.req.model.account.QrcodeCreate;
import com.jeeplus.weixin.api.core.req.model.account.QrcodeScene;
import com.jeeplus.weixin.api.core.req.model.account.ShortUrlCreate;
import com.jeeplus.weixin.api.core.util.WeiXinConstant;
import com.jeeplus.weixin.api.wxaccount.model.WxQrcode;

/**
 * 微信--生成二维码和长短链接切换
 * 对应微信API的“帐号管理”
 * http://mp.weixin.qq.com/wiki/18/167e7d94df85d8389df6c94a7a8f78ba.html
 * Created by yuzp17311 on 2016/8/23.
 */
public class JwAccountAPI {

	/**
	 * 二维码类型，QR_SCENE为临时, QR_LIMIT_SCENE为永久, QR_LIMIT_STR_SCENE为永久的字符串参数值
	 */
	public static final String QRCODE_TYPE_SCENE = "QR_SCENE";
	public static final String QRCODE_TYPE_LIMIT = "QR_LIMIT_SCENE";
	public static final String QRCODE_TYPE_LIMIT_STR = "QR_LIMIT_STR_SCENE";
	
	/**
	 * 代表长链接转短链接
	 */
	public static final String SHORT_URL_ACTION = "long2short";

	/**
	 * 生成带参数的二维码
     * http://mp.weixin.qq.com/wiki/18/167e7d94df85d8389df6c94a7a8f78ba.html
	 * expire_seconds	该二维码有效时间，以秒为单位。 最大不超过1800。
		action_name	二维码类型，QR_SCENE为临时,QR_LIMIT_SCENE为永久,QR_LIMIT_STR_SCENE为永久的字符串参数值
		action_info	二维码详细信息
		scene_id	场景值ID，临时二维码时为32位非0整型，永久二维码时最大值为100000（目前参数只支持1--100000）
		scene_str	场景值ID（字符串形式的ID），字符串类型，长度限制为1到64，仅永久二维码支持此字段
	 * @param access_token 调用接口凭证
	 * @param scene_str
	 * @param action_name
	 * @param expire_seconds
	 * @return
	 * @throws WexinReqException
	 */
	public static WxQrcode createQrcode(String access_token, String scene_str,
			String action_name, String expire_seconds) throws WexinReqException {
		QrcodeCreate qrcodeCreate = new QrcodeCreate();
		qrcodeCreate.setAccess_token(access_token);
		QrcodeActionInfo q = new QrcodeActionInfo();
		QrcodeScene ss = new QrcodeScene();
		ss.setScene_str(scene_str);
		q.setScene(ss);
		qrcodeCreate.setAction_info(q);
		qrcodeCreate.setExpire_seconds(expire_seconds);
		qrcodeCreate.setAction_name(action_name);
		JSONObject result = WeiXinReqService.getInstance().doWeinxinReqJson(qrcodeCreate);
		Object error = result.get(WeiXinConstant.RETURN_ERROR_INFO_CODE);
		WxQrcode wxQrcode = null;
		wxQrcode = (WxQrcode) JSONObject.toBean(result, WxQrcode.class);
		return wxQrcode;
	}

	/**
	 * 长链接转短链接接口
     * http://mp.weixin.qq.com/wiki/6/856aaeb492026466277ea39233dc23ee.html
	 * @param access_token 调用接口凭证
	 * @param long_url 需要转换的长链接，支持http://、https://、weixin://wxpay 格式的url
	 * @return
	 * @throws WexinReqException
	 */
	public static String getShortUrl(String access_token,String long_url) throws WexinReqException{
		ShortUrlCreate uc = new ShortUrlCreate();
		uc.setAccess_token(access_token);
		uc.setLong_url(long_url);
		uc.setAction(SHORT_URL_ACTION);
		JSONObject result = WeiXinReqService.getInstance().doWeinxinReqJson(uc);
		Object error = result.get("short_url");
		String shortUrl = "";
		if (error != null) {
			shortUrl = result.getString("short_url");
		}else{
			shortUrl = result.getString(WeiXinConstant.RETURN_ERROR_INFO_MSG);
		}
		return shortUrl;
	}
	
}
