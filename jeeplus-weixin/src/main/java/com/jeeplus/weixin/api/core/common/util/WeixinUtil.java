package com.jeeplus.weixin.api.core.common.util;

import java.util.HashMap;
import java.util.Map;

/**
 * 微信http请求的辅助类
 * Created by yuzp17311 on 2016/8/22.
 */
public class WeixinUtil {

    /**
     * 解析http的请求,一重载
     * @param url 请求的微信服务器url
     * @param paras 请求的参数
     * @return 请求的结果
     */
	public static String parseWeiXinHttpUrl(String url,Map<String, Object> paras) {
		return FreemarkerUtil.parseTemplateContent(url, paras);
	}

    /**
     * 解析http的请求,二重载
     * @param url 请求的微信服务器url
     * @param access_token 公众号的全局唯一接口调用凭据
     * @return 请求的结果
     */
	public static String parseWeiXinHttpUrl(String url,String access_token) {
		Map<String,Object> paras = new HashMap<String,Object>();
		paras.put("ACCESS_TOKEN", access_token);
		return FreemarkerUtil.parseTemplateContent(url, paras);
	}
}
