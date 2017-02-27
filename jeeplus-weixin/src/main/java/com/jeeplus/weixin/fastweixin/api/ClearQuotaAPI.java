package com.jeeplus.weixin.fastweixin.api;

import com.jeeplus.weixin.fastweixin.api.config.ApiConfig;
import com.jeeplus.weixin.fastweixin.api.response.BaseResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 接口调用频次清零API <br>
 * 公众号调用接口并不是无限制的。为了防止公众号的程序错误而引发微信服务器负载异常，默认情况下，每个公众号调用接口都不能超过一定限制，当超过一定限制时，调用对应接口会收到如下错误返回码：
 * {"errcode":45009,"errmsg":"api freq out of limit"}
 * @author:yuzp17311
 * @version:v1.0
 * @date: 2017-02-27 17:28.
 */
public class ClearQuotaAPI extends BaseAPI {

    private static final Logger logger= LoggerFactory.getLogger(ClearQuotaAPI.class);

    /**
     * 构造方法，设置apiConfig
     *
     * @param config 微信API配置对象
     */
    protected ClearQuotaAPI(ApiConfig config) {
        super(config);
    }

    /**
     * 公众号调用或第三方平台帮公众号调用对公众号的所有api调用（包括第三方帮其调用）次数进行清零
     * @return BaseResponse
     */
    public  BaseResponse clearQuota(){
        String url=BASE_API_URL+"/cgi-bin/clear_quota?access_token=#";
        BaseResponse response=executePost(url,null);
        return response;
    }


}
