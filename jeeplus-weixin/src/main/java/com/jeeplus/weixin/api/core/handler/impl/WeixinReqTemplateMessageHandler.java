package com.jeeplus.weixin.api.core.handler.impl;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import com.jeeplus.weixin.api.core.annotation.ReqType;
import com.jeeplus.weixin.api.core.exception.WexinReqException;
import com.jeeplus.weixin.api.core.handler.WeiXinReqHandler;
import com.jeeplus.weixin.api.core.req.model.WeixinReqConfig;
import com.jeeplus.weixin.api.core.req.model.WeixinReqParam;
import com.jeeplus.weixin.api.core.req.model.message.IndustryTemplateMessageSend;
import com.jeeplus.weixin.api.core.req.model.message.TemplateMessage;
import com.jeeplus.weixin.api.core.util.HttpRequestProxy;
import com.jeeplus.weixin.api.core.util.WeiXinReqUtil;

import com.google.gson.Gson;

/**
 * 模板消息发送
 * 对应微信API的“消息管理”--“模版消息”
 * http://mp.weixin.qq.com/wiki/5/6dde9eaa909f83354e0094dc3ad99e05.html#.E5.8F.91.E9.80.81.E6.A8.A1.E6.9D.BF.E6.B6.88.E6.81.AF
 * Created by yuzp17311 on 2016/8/22.
 *
 */
public class WeixinReqTemplateMessageHandler implements WeiXinReqHandler {

	private static Logger logger = Logger.getLogger(WeixinReqTemplateMessageHandler.class);
	
	@SuppressWarnings("rawtypes")
	public String doRequest(WeixinReqParam weixinReqParam) throws WexinReqException{
		// TODO Auto-generated method stub
		String strReturnInfo = "";
		if(weixinReqParam.getClass().isAnnotationPresent(ReqType.class)){
			ReqType reqType = weixinReqParam.getClass().getAnnotation(ReqType.class);
			WeixinReqConfig objConfig = WeiXinReqUtil.getWeixinReqConfig(reqType.value());
			if(objConfig != null){
				String reqUrl = objConfig.getUrl();
				IndustryTemplateMessageSend mc = (IndustryTemplateMessageSend) weixinReqParam;
				Map<String, String> parameters = new HashMap<String, String>();
				parameters.put("access_token", mc.getAccess_token());
				String jsonData = getMsgJson(mc) ;
				logger.info("处理模板消息"+jsonData);
				strReturnInfo = HttpRequestProxy.doJsonPost(reqUrl, parameters, jsonData);
			}
		}else{
			logger.info("没有找到对应的配置信息");
		}
		return strReturnInfo;
	}

	/**
	 * 单独处理 json信息
	 * @param mc 企业模版消息
	 * @return 消息的字符串
	 */
	private  String getMsgJson(IndustryTemplateMessageSend mc){
		StringBuffer json = new StringBuffer();
		Gson gson = new Gson();
		TemplateMessage tm = mc.getData();
		mc.setData(null);
		String objJson = gson.toJson(mc);
		mc.setData(tm);
		json.append(objJson);
		json.setLength(json.length()-1);
		json.append(",");
		json.append("\"data\":{");
		
		objJson = gson.toJson(tm.getFirst());
		json.append(" \"first\":");
		json.append(objJson);
		json.append(",");
		objJson = gson.toJson(tm.getKeynote1());
		json.append(" \"keynote1\":");
		json.append(objJson);
		json.append(",");
		objJson = gson.toJson(tm.getKeynote2());
		json.append(" \"keynote2\":");
		json.append(objJson);
		json.append(",");
		objJson = gson.toJson(tm.getKeynote3());
		json.append(" \"keynote3\":");
		json.append(objJson);
		json.append(",");
		objJson = gson.toJson(tm.getRemark());
		json.append(" \"remark\":");
		json.append(objJson);
		json.append("}}");
		return json.toString();
	}
	
}
