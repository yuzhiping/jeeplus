package com.jeeplus.weixin.fastweixin.api;

import com.jeeplus.weixin.fastweixin.api.config.ApiConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 老版本支付，新版本使用PayMchAPI
 * @author:yuzp17311
 * @version:v1.0
 * @date: 2017-02-27 18:12.
 */
public class PayAPI extends BaseAPI {

    private static final Logger logger= LoggerFactory.getLogger(PayAPI.class);

    /**
     * 构造方法，设置apiConfig
     *
     * @param config 微信API配置对象
     */
    protected PayAPI(ApiConfig config) {
        super(config);
    }
}
