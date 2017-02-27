package com.jeeplus.weixin.fastweixin.api;

import com.jeeplus.weixin.fastweixin.api.config.ApiConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 微信小店
 * @author:yuzp17311
 * @version:v1.0
 * @date: 2017-02-27 17:51.
 */
public class MerchantAPI extends BaseAPI {

    private static final Logger logger= LoggerFactory.getLogger(MerchantAPI.class);

    /**
     * 构造方法，设置apiConfig
     *
     * @param config 微信API配置对象
     */
    protected MerchantAPI(ApiConfig config) {
        super(config);
    }
}
