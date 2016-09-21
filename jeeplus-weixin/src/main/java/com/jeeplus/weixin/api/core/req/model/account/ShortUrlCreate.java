package com.jeeplus.weixin.api.core.req.model.account;

import com.jeeplus.weixin.api.core.annotation.ReqType;
import com.jeeplus.weixin.api.core.req.model.WeixinReqParam;

/**
 * Created by yuzp17311 on 2016/8/22.
 */
@ReqType("shorturlCreate")
public class ShortUrlCreate  extends WeixinReqParam{

    private String action = "long2short";

    private String long_url;

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getLong_url() {
        return long_url;
    }

    public void setLong_url(String long_url) {
        this.long_url = long_url;
    }
}
