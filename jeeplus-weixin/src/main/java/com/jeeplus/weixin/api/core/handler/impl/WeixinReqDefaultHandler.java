package com.jeeplus.weixin.api.core.handler.impl;

import java.util.Map;

import org.apache.log4j.Logger;
import com.jeeplus.weixin.api.core.annotation.ReqType;
import com.jeeplus.weixin.api.core.exception.WexinReqException;
import com.jeeplus.weixin.api.core.handler.WeiXinReqHandler;
import com.jeeplus.weixin.api.core.req.model.WeixinReqConfig;
import com.jeeplus.weixin.api.core.req.model.WeixinReqParam;
import com.jeeplus.weixin.api.core.util.HttpRequestProxy;
import com.jeeplus.weixin.api.core.util.WeiXinConstant;
import com.jeeplus.weixin.api.core.util.WeiXinReqUtil;

/**
 * 微信http请求接口的默认实现
 * Created by yuzp17311 on 2016/8/22.
 */
public class WeixinReqDefaultHandler implements WeiXinReqHandler {

	private static Logger logger = Logger.getLogger(WeixinReqDefaultHandler.class);

    /**
     * 发起请求
     * @param weixinReqParam
     * @return
     * @throws WexinReqException
     */
	public String doRequest(WeixinReqParam weixinReqParam) throws WexinReqException{

		String strReturnInfo = "";
		if(weixinReqParam.getClass().isAnnotationPresent(ReqType.class)){
			ReqType reqType = weixinReqParam.getClass().getAnnotation(ReqType.class);
			WeixinReqConfig objConfig = WeiXinReqUtil.getWeixinReqConfig(reqType.value());
			if(objConfig != null){
				String reqUrl = objConfig.getUrl();
				String method = objConfig.getMethod();
				String datatype = objConfig.getDatatype();
				Map parameters = WeiXinReqUtil.getWeixinReqParam(weixinReqParam);
				if(WeiXinConstant.JSON_DATA_TYPE.equalsIgnoreCase(datatype)){
					parameters.clear();
					parameters.put("access_token", weixinReqParam.getAccess_token());
					weixinReqParam.setAccess_token(null);
					String jsonData = WeiXinReqUtil.getWeixinParamJson(weixinReqParam);
					strReturnInfo = HttpRequestProxy.doJsonPost(reqUrl, parameters, jsonData);
				}else{
					if(WeiXinConstant.REQUEST_GET.equalsIgnoreCase(method)){
						strReturnInfo = HttpRequestProxy.doGet(reqUrl, parameters, "UTF-8");
					}else{
						strReturnInfo = HttpRequestProxy.doPost(reqUrl, parameters, "UTF-8");
					}
				}
			}
		}else{
			logger.info("没有找到对应的配置信息");
		}
		return strReturnInfo;
	}

}
