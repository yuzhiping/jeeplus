package com.jeeplus.common.alipay.util;

/**
 * @author:yuzp17311
 * @version:v1.0
 * @date: 2017-01-06 11:25.
 */
public class AliPayConfigureUtils {

    // 开发者应用私钥。java配置PKCS8格式，PHP/.Net语言配置rsa_private_key.pem文件中原始私钥。
    public static final String RSA_RRIVATE_KEY ="jeeplus";
    // 接口请求网关。当面付支付、查询、退款、撤销接口中为固定值
    public static final String URL = "https://openapi.alipaydev.com/gateway.do";
    // 商户应用APPID，只要您的应用中包含当面付接口且是开通状态，就可以用此应用对应的appid。开发者可登录开放平台-管理中心-对应应用中查看
    public static final String APPID = "jeeplus";
    // 编码字符集。默认 utf-8
    public static final String CHARSET = "utf-8";
    // 返回格式。默认json
    public static final String FORMAT = "json";
    // 支付宝公钥，用于获取同步返回信息后进行验证，验证是否是支付宝发送的信息。
    public static final String ALIPAY_PUBLIC_KEY = "jeeplus";

}
