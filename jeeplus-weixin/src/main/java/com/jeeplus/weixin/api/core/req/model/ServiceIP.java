package com.jeeplus.weixin.api.core.req.model;

import com.jeeplus.weixin.api.core.annotation.ReqType;

/**
 * 如果公众号基于安全等考虑，需要获知微信服务器的IP地址列表
 * Created by yuzp17311 on 2016/8/22.
 */
@ReqType("getcallbackip")
public class ServiceIP extends WeixinReqParam {
    
}
