package com.jeeplus.weixin.pay;

import com.jeeplus.weixin.pay.util.ConfigureUtils;
import com.jeeplus.weixin.pay.util.HttpsRequestUtils;
import com.jeeplus.weixin.pay.util.PayCommonUtil;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 微信支付SDK的入口
 * @author:yuzp17311
 * @version:v1.0
 * @date: 2017-01-05 19:47.
 */
public class WXPay {

    public String getRemortIP(HttpServletRequest request) {
        if (request.getHeader("x-forwarded-for") == null) {
            return request.getRemoteAddr();
        }
        return request.getHeader("x-forwarded-for");
    }
    /**
     * 初始化SDK依赖的几个关键配置
     *
     * @param key
     *            签名算法需要用到的秘钥
     * @param appID
     *            公众账号ID
     * @param mchID
     *            商户ID
     * @param sdbMchID
     *            子商户ID，受理模式必填
     * @param certLocalPath
     *            HTTP证书在服务器中的路径，用来加载证书用
     * @param certPassword
     *            HTTP证书的密码，默认等于MCHID
     */
    public static void initSDKConfiguration(String key, String appID, String mchID, String sdbMchID,
                                            String certLocalPath, String certPassword) {
        ConfigureUtils.setKey(key);
        ConfigureUtils.setAppID(appID);
        ConfigureUtils.setMchID(mchID);
        ConfigureUtils.setSubMchID(sdbMchID);
        ConfigureUtils.setCertLocalPath(certLocalPath);
        ConfigureUtils.setCertPassword(certPassword);
    }

    /**
     *
     *
     * @param out_trade_no
     * @return
     * @throws Exception
     */
    public static String weixin_pay(String out_trade_no) throws Exception {
        HttpsRequestUtils httpRequest = new HttpsRequestUtils();
        // 账号信息
        String appid = ConfigureUtils.getAppid(); // appid
        // String appsecret = PayConfigUtil.APP_SECRET; // appsecret
        // 商业号
        String mch_id = ConfigureUtils.getMchid();
        // key
        String key = ConfigureUtils.getKey();

        String currTime = PayCommonUtil.getCurrTime();
        String strTime = currTime.substring(8, currTime.length());
        String strRandom = PayCommonUtil.buildRandom(4) + "";
        // 随机字符串
        String nonce_str = strTime + strRandom;
        // 价格 注意：价格的单位是分
        String order_price = "1";
        // 商品名称
        // String body = "luozhuang";

        // 获取发起电脑 ip
        String spbill_create_ip = ConfigureUtils.getIP();
        // 回调接口
        String notify_url = ConfigureUtils.NOTIFY_URL;
        String product_id="luozhuang";
        String trade_type = "NATIVE";//JSAPI--公众号支付、NATIVE--原生扫码支付、APP--app支付
        String time_start = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
        Calendar ca = Calendar.getInstance();
        ca.setTime(new Date());
        ca.add(Calendar.DATE, 1);
        String time_expire = new SimpleDateFormat("yyyyMMddHHmmss").format(ca.getTime());
        WXPayData packageParams = new WXPayData();
        packageParams.put("appid", appid);
        packageParams.put("mch_id", mch_id);
        packageParams.put("nonce_str", nonce_str);
        packageParams.put("body", "luozhuang-服务费");
        packageParams.put("out_trade_no", out_trade_no);
        packageParams.put("product_id",product_id);
        packageParams.put("total_fee", order_price);
        packageParams.put("spbill_create_ip", spbill_create_ip);
        packageParams.put("notify_url", notify_url);
        packageParams.put("trade_type", trade_type);
        packageParams.put("time_start", time_start);
        packageParams.put("time_expire", time_expire);
        String sign = packageParams.MakeSign();
        packageParams.put("sign", sign);
        WXPayData resXml= WXPayAPI.UnifiedOrder(packageParams, 20);
        String requestXML = packageParams.ToXml();
        System.out.println("请求xml：：：：" + requestXML);
        System.out.println("得到xml：：：：" + resXml.toString());
        // String return_code = (String) map.get("return_code");
        // String prepay_id = (String) map.get("prepay_id");
        String urlCode = (String) resXml.GetValue("code_url");
        System.out.println("打印调用统一下单接口生成二维码url:::::" + urlCode);
        return urlCode;
    }

}
