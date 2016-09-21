package com.jeeplus.weixin.api.core.common;

import net.sf.json.JSONObject;

/**
 * 用于获取access_token(公众号的全局唯一接口调用凭据)
 * Created by yuzp17311 on 2016/8/22.
 */
public class AccessToken {

    /**
     * 第三方用户唯一凭证
     */
    private String appid;
    /**
     * 第三方用户唯一凭证密钥
     */
    private String appscret;

    public String getAppid() {
        return appid;
    }

    public void setAppid(String appid) {
        this.appid = appid;
    }

    public String getAppscret() {
        return appscret;
    }

    public void setAppscret(String appscret) {
        this.appscret = appscret;
    }

    public AccessToken(String appid, String appscret) {
        this.setAppid(appid);
        this.setAppscret(appscret);
    }

    /**
     * 获取access_token
     * http://mp.weixin.qq.com/wiki/14/9f9c82c1af308e3b14ba9b973f99a8ba.html
     * @return
     */
    public String getNewAccessToken(){
        String token=null;
        String requestUrl="https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET\".replace(\"APPID\", this.getAppid()).replace(\"APPSECRET\", this.getAppscret())";
        JSONObject jsonObject = WeixinHttpUtils.httpRequest(requestUrl, "GET", null);
        if (null != jsonObject) {
            try {
                token = jsonObject.getString("access_token");
            } catch (Exception e) {
                token = null;
            }
        }
        return token;
    }
}
