package com.jeeplus.weixin.api.core.handler;

import com.jeeplus.weixin.api.core.exception.WexinReqException;
import com.jeeplus.weixin.api.core.req.model.WeixinReqParam;

/**
 * 获取微信接口信息的Handler接口
 * Created by yuzp17311 on 2016/8/22.
 */
public interface WeiXinReqHandler    {
     String doRequest(WeixinReqParam weixinReqParam) throws WexinReqException;
}
