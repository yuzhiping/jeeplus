package com.jeeplus.weixin.pay;

import com.jeeplus.weixin.fastweixin.exception.WxPayException;
import com.jeeplus.weixin.fastweixin.util.DateTimeUtils;
import com.jeeplus.weixin.pay.util.ConfigureUtils;
import com.jeeplus.weixin.pay.util.HttpsRequestUtils;
import com.jeeplus.weixin.pay.util.UUIDGeneratorUtils;
import jdk.internal.org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.util.Calendar;
import java.util.Random;

/**
 * @author:yuzp17311
 * @version:v1.0
 * @date: 2017-01-05 19:46.
 */
public class WXPayAPI {

    private static final String DEFAULT_CODE="jeeplus-weixin";
    private static final int REPORT_LEVENL = 0;

    /**
     * 提交被扫支付API
     * 收银员使用扫码设备读取微信用户刷卡授权码以后，二维码或条码信息传送至商户收银台，
     * 由商户收银台或者商户后台调用该接口发起支付。
     * @param inputObj inputObj 提交给被扫支付API的参数
     * @param timeOut timeOut 超时时间
     * @throws WxPayException
     * @return 成功时返回调用结果，其他抛异常
     * @throws IOException
     * @throws KeyStoreException
     * @throws NoSuchAlgorithmException
     * @throws KeyManagementException
     * @throws UnrecoverableKeyException
     * @throws SAXException
     * @throws ParserConfigurationException
     */
    public static WXPayData Micropay(WXPayData inputObj, int timeOut) throws UnrecoverableKeyException, KeyManagementException, NoSuchAlgorithmException, KeyStoreException, IOException, WxPayException, ParserConfigurationException, SAXException, org.xml.sax.SAXException {
        String url = ConfigureUtils.PAY_API;
        //检测必填参数
        if (!inputObj.IsSet("body"))
        {
            throw new WxPayException("提交被扫支付API接口中，缺少必填参数body！");
        }
        else if (!inputObj.IsSet("out_trade_no"))
        {
            throw new WxPayException("提交被扫支付API接口中，缺少必填参数out_trade_no！");
        }
        else if (!inputObj.IsSet("total_fee"))
        {
            throw new WxPayException("提交被扫支付API接口中，缺少必填参数total_fee！");
        }
        else if (!inputObj.IsSet("auth_code"))
        {
            throw new WxPayException("提交被扫支付API接口中，缺少必填参数auth_code！");
        }

        inputObj.SetValue("spbill_create_ip", ConfigureUtils.getIP());//终端ip
        inputObj.SetValue("appid", ConfigureUtils.getAppid());//公众账号ID
        inputObj.SetValue("mch_id", ConfigureUtils.getMchid());//商户号
        inputObj.SetValue("nonce_str", UUIDGeneratorUtils.getUUID().toString().replace("-", ""));//随机字符串
        inputObj.SetValue("sign", inputObj.MakeSign());//签名
        String xml = inputObj.ToXml();

        HttpsRequestUtils https=new HttpsRequestUtils();
        https.setSocketTimeout(timeOut);
        String response = https.sendPost(url, xml);//调用HTTP通信接口以提交数据到API
        WXPayData result = new WXPayData();
        result.FromXml(response);



        return result;
    }


    /**
     *
     * 查询订单
     * @param  timeOut 超时时间
     * @throws WxPayException
     * @return 成功时返回订单查询结果，其他抛异常
     * @throws IOException
     * @throws KeyStoreException
     * @throws NoSuchAlgorithmException
     * @throws KeyManagementException
     * @throws UnrecoverableKeyException
     * @throws SAXException
     * @throws ParserConfigurationException
     */
    public static WXPayData OrderQuery(WXPayData inputObj, int timeOut) throws UnrecoverableKeyException,
            KeyManagementException, NoSuchAlgorithmException, KeyStoreException, IOException, WxPayException,
            ParserConfigurationException, SAXException, org.xml.sax.SAXException {
        String url = ConfigureUtils.PAY_QUERY_API;
        //检测必填参数
        if (!inputObj.IsSet("out_trade_no") && !inputObj.IsSet("transaction_id"))
        {
            throw new WxPayException("订单查询接口中，out_trade_no、transaction_id至少填一个！");
        }

        inputObj.SetValue("appid", ConfigureUtils.getAppid());//公众账号ID
        inputObj.SetValue("mch_id", ConfigureUtils.getMchid());//商户号
        inputObj.SetValue("nonce_str", WXPayAPI.GenerateNonceStr());//随机字符串
        inputObj.SetValue("sign", inputObj.MakeSign());//签名

        String xml = inputObj.ToXml();

        HttpsRequestUtils https=new HttpsRequestUtils();
        https.setSocketTimeout(timeOut);
        String response = https.sendPost(url, xml);//调用HTTP通信接口以提交数据到API
        WXPayData result = new WXPayData();
        result.FromXml(response);

        return result;
    }


    /**
     *
     * 撤销订单API接口
     * @param  inputObj 提交给撤销订单API接口的参数，out_trade_no和transaction_id必填一个
     * @param  timeOut 接口超时时间
     * @throws WxPayException
     * @return 成功时返回API调用结果，其他抛异常
     * @throws IOException
     * @throws KeyStoreException
     * @throws NoSuchAlgorithmException
     * @throws KeyManagementException
     * @throws UnrecoverableKeyException
     * @throws SAXException
     * @throws ParserConfigurationException
     */
    public static WXPayData Reverse(WXPayData inputObj, int timeOut) throws WxPayException, UnrecoverableKeyException,
            KeyManagementException, NoSuchAlgorithmException, KeyStoreException, IOException, ParserConfigurationException,
            SAXException, org.xml.sax.SAXException {
        String url = ConfigureUtils.REVERSE_API;
        //检测必填参数
        if (!inputObj.IsSet("out_trade_no") && !inputObj.IsSet("transaction_id"))
        {
            throw new WxPayException("撤销订单API接口中，参数out_trade_no和transaction_id必须填写一个！");
        }

        inputObj.SetValue("appid", ConfigureUtils.getAppid());//公众账号ID
        inputObj.SetValue("mch_id", ConfigureUtils.getMchid());//商户号
        inputObj.SetValue("nonce_str", GenerateNonceStr());//随机字符串
        inputObj.SetValue("sign", inputObj.MakeSign());//签名
        String xml = inputObj.ToXml();


        HttpsRequestUtils https=new HttpsRequestUtils();
        https.setSocketTimeout(timeOut);
        String response = https.sendPost(url, xml);//调用HTTP通信接口以提交数据到API
        WXPayData result = new WXPayData();
        result.FromXml(response);

        return result;
    }


    /**
     *
     * 申请退款
     * @param  inputObj 提交给申请退款API的参数
     * @param  timeOut 超时时间
     * @throws WxPayException
     * @return 成功时返回接口调用结果，其他抛异常
     * @throws IOException
     * @throws KeyStoreException
     * @throws NoSuchAlgorithmException
     * @throws KeyManagementException
     * @throws UnrecoverableKeyException
     * @throws SAXException
     * @throws ParserConfigurationException
     */
    public static WXPayData Refund(WXPayData inputObj, int timeOut) throws WxPayException, UnrecoverableKeyException,
            KeyManagementException, NoSuchAlgorithmException, KeyStoreException, IOException, ParserConfigurationException,
            SAXException, org.xml.sax.SAXException {
        String url = ConfigureUtils.REFUND_API;
        //检测必填参数
        if (!inputObj.IsSet("out_trade_no") && !inputObj.IsSet("transaction_id"))
        {
            throw new WxPayException("退款申请接口中，out_trade_no、transaction_id至少填一个！");
        }
        else if (!inputObj.IsSet("out_refund_no"))
        {
            throw new WxPayException("退款申请接口中，缺少必填参数out_refund_no！");
        }
        else if (!inputObj.IsSet("total_fee"))
        {
            throw new WxPayException("退款申请接口中，缺少必填参数total_fee！");
        }
        else if (!inputObj.IsSet("refund_fee"))
        {
            throw new WxPayException("退款申请接口中，缺少必填参数refund_fee！");
        }
        else if (!inputObj.IsSet("op_user_id"))
        {
            throw new WxPayException("退款申请接口中，缺少必填参数op_user_id！");
        }

        inputObj.SetValue("appid", ConfigureUtils.getAppid());//公众账号ID
        inputObj.SetValue("mch_id", ConfigureUtils.getMchid());//商户号
        inputObj.SetValue("nonce_str", UUIDGeneratorUtils.getUUID(DEFAULT_CODE).toString());//随机字符串
        inputObj.SetValue("sign", inputObj.MakeSign());//签名

        String xml = inputObj.ToXml();

        HttpsRequestUtils https=new HttpsRequestUtils();
        https.setSocketTimeout(timeOut);
        String response = https.sendPost(url, xml);//调用HTTP通信接口以提交数据到API
        WXPayData result = new WXPayData();
        result.FromXml(response);

        return result;
    }


    /**
     *
     * 查询退款
     * 提交退款申请后，通过该接口查询退款状态。退款有一定延时，
     * 用零钱支付的退款20分钟内到账，银行卡支付的退款3个工作日后重新查询退款状态。
     * out_refund_no、out_trade_no、transaction_id、refund_id四个参数必填一个
     * @param  inputObj 提交给查询退款API的参数
     * @param  timeOut 接口超时时间
     * @throws WxPayException
     * @return 成功时返回，其他抛异常
     * @throws IOException
     * @throws KeyStoreException
     * @throws NoSuchAlgorithmException
     * @throws KeyManagementException
     * @throws UnrecoverableKeyException
     * @throws SAXException
     * @throws ParserConfigurationException
     */
    public static WXPayData RefundQuery(WXPayData inputObj, int timeOut) throws UnrecoverableKeyException,
            KeyManagementException, NoSuchAlgorithmException, KeyStoreException, IOException, WxPayException,
            ParserConfigurationException, SAXException, org.xml.sax.SAXException {
        String url = ConfigureUtils.REFUND_QUERY_API;
        //检测必填参数
        if(!inputObj.IsSet("out_refund_no") && !inputObj.IsSet("out_trade_no") &&
                !inputObj.IsSet("transaction_id") && !inputObj.IsSet("refund_id"))
        {
            throw new WxPayException("退款查询接口中，out_refund_no、out_trade_no、transaction_id、refund_id四个参数必填一个！");
        }

        inputObj.SetValue("appid", ConfigureUtils.getAppid());//公众账号ID
        inputObj.SetValue("mch_id", ConfigureUtils.getMchid());//商户号
        inputObj.SetValue("nonce_str",GenerateNonceStr());//随机字符串
        inputObj.SetValue("sign",inputObj.MakeSign());//签名

        String xml = inputObj.ToXml();


        HttpsRequestUtils https=new HttpsRequestUtils();
        https.setSocketTimeout(timeOut);
        String response = https.sendPost(url, xml);//调用HTTP通信接口以提交数据到API
        WXPayData result = new WXPayData();
        result.FromXml(response);

        return result;
    }


    /**
     * 下载对账单
     * @param  inputObj 提交给下载对账单API的参数
     * @param  timeOut 接口超时时间
     * @throws WxPayException
     * @return 成功时返回，其他抛异常
     * @throws IOException
     * @throws NoSuchAlgorithmException
     * @throws KeyStoreException
     * @throws KeyManagementException
     * @throws UnrecoverableKeyException
     * @throws SAXException
     * @throws ParserConfigurationException
     */
    public static WXPayData DownloadBill(WXPayData inputObj, int timeOut) throws UnrecoverableKeyException,
            KeyManagementException, KeyStoreException, NoSuchAlgorithmException, IOException, WxPayException,
            ParserConfigurationException, SAXException, org.xml.sax.SAXException {
        String url = ConfigureUtils.DOWNLOAD_BILL_API;
        //检测必填参数
        if (!inputObj.IsSet("bill_date"))
        {
            throw new WxPayException("对账单接口中，缺少必填参数bill_date！");
        }

        inputObj.SetValue("appid", ConfigureUtils.getAppid());//公众账号ID
        inputObj.SetValue("mch_id", ConfigureUtils.getMchid());//商户号
        inputObj.SetValue("nonce_str", GenerateNonceStr());//随机字符串
        inputObj.SetValue("sign", inputObj.MakeSign());//签名

        String xml = inputObj.ToXml();

        HttpsRequestUtils https=new HttpsRequestUtils();
        https.setSocketTimeout(timeOut);
        String response = https.sendPost(url, xml);//调用HTTP通信接口以提交数据到API

        WXPayData result = new WXPayData();
        //若接口调用失败会返回xml格式的结果
        if (response.startsWith("<xml>"))
        {

            result.FromXml(response);
        }
        //接口调用成功则返回非xml格式的数据
        else
            result.SetValue("result", response);

        return result;
    }


    /**
     *
     * 转换短链接
     * 该接口主要用于扫码原生支付模式一中的二维码链接转成短链接(weixin://wxpay/s/XXXXXX)，
     * 减小二维码数据量，提升扫描速度和精确度。
     * @param  inputObj 提交给转换短连接API的参数
     * @param  timeOut 接口超时时间
     * @throws WxPayException
     * @return 成功时返回，其他抛异常
     * @throws IOException
     * @throws KeyStoreException
     * @throws NoSuchAlgorithmException
     * @throws KeyManagementException
     * @throws UnrecoverableKeyException
     * @throws SAXException
     * @throws ParserConfigurationException
     */
    public static WXPayData ShortUrl(WXPayData inputObj, int timeOut) throws WxPayException, UnrecoverableKeyException,
            KeyManagementException, NoSuchAlgorithmException, KeyStoreException, IOException, ParserConfigurationException,
            SAXException, org.xml.sax.SAXException {
        String url = ConfigureUtils.Shorturl_API;
        //检测必填参数
        if(!inputObj.IsSet("long_url"))
        {
            throw new WxPayException("需要转换的URL，签名用原串，传输需URL encode！");
        }

        inputObj.SetValue("appid", ConfigureUtils.getAppid());//公众账号ID
        inputObj.SetValue("mch_id", ConfigureUtils.getMchid());//商户号
        inputObj.SetValue("nonce_str",GenerateNonceStr());//随机字符串
        inputObj.SetValue("sign",inputObj.MakeSign());//签名
        String xml = inputObj.ToXml();

        HttpsRequestUtils https=new HttpsRequestUtils();
        https.setSocketTimeout(timeOut);
        String response = https.sendPost(url, xml);//调用HTTP通信接口以提交数据到API
        WXPayData result = new WXPayData();
        result.FromXml(response);




        return result;
    }


    /**
     *
     * 统一下单
     * @param  inputObj 提交给统一下单API的参数
     * @param  timeOut 超时时间
     * @throws WxPayException
     * @return 成功时返回，其他抛异常
     * @throws IOException
     * @throws KeyStoreException
     * @throws NoSuchAlgorithmException
     * @throws KeyManagementException
     * @throws UnrecoverableKeyException
     * @throws SAXException
     * @throws ParserConfigurationException
     */
    public static WXPayData UnifiedOrder(WXPayData inputObj, int timeOut) throws WxPayException, UnrecoverableKeyException,
            KeyManagementException, NoSuchAlgorithmException, KeyStoreException, IOException, ParserConfigurationException,
            SAXException, org.xml.sax.SAXException {
        String url = ConfigureUtils.UnifiedOrder_API;
        //检测必填参数
        if (!inputObj.IsSet("out_trade_no"))
        {
            throw new WxPayException("缺少统一支付接口必填参数out_trade_no！");
        }
        else if (!inputObj.IsSet("body"))
        {
            throw new WxPayException("缺少统一支付接口必填参数body！");
        }
        else if (!inputObj.IsSet("total_fee"))
        {
            throw new WxPayException("缺少统一支付接口必填参数total_fee！");
        }
        else if (!inputObj.IsSet("trade_type"))
        {
            throw new WxPayException("缺少统一支付接口必填参数trade_type！");
        }

        //关联参数
        if (inputObj.GetValue("trade_type").toString() == "JSAPI" && !inputObj.IsSet("openid"))
        {
            throw new WxPayException("统一支付接口中，缺少必填参数openid！trade_type为JSAPI时，openid为必填参数！");
        }
        if (inputObj.GetValue("trade_type").toString() == "NATIVE" && !inputObj.IsSet("product_id"))
        {
            throw new WxPayException("统一支付接口中，缺少必填参数product_id！trade_type为JSAPI时，product_id为必填参数！");
        }

        //异步通知url未设置，则使用配置文件中的url
        if (!inputObj.IsSet("notify_url"))
        {
            inputObj.SetValue("notify_url", ConfigureUtils.NOTIFY_URL);//异步通知url
        }

        inputObj.SetValue("appid", ConfigureUtils.getAppid());//公众账号ID
        inputObj.SetValue("mch_id", ConfigureUtils.getMchid());//商户号
        inputObj.SetValue("spbill_create_ip", ConfigureUtils.getIP());//终端ip
        inputObj.SetValue("nonce_str", GenerateNonceStr());//随机字符串

        //签名
        inputObj.SetValue("sign", inputObj.MakeSign());
        String xml = inputObj.ToXml();


        HttpsRequestUtils https=new HttpsRequestUtils();
        https.setSocketTimeout(timeOut);
        String response = https.sendPost(url, xml);//调用HTTP通信接口以提交数据到API
        WXPayData result = new WXPayData();
        result.FromXml(response);



        return result;
    }


    /**
     *
     * 关闭订单
     * @param  timeOut 接口超时时间
     * @throws WxPayException
     * @return 成功时返回，其他抛异常
     * @throws IOException
     * @throws KeyStoreException
     * @throws NoSuchAlgorithmException
     * @throws KeyManagementException
     * @throws UnrecoverableKeyException
     * @throws SAXException
     * @throws ParserConfigurationException
     */
    public static WXPayData CloseOrder(WXPayData inputObj, int timeOut) throws WxPayException, UnrecoverableKeyException,
            KeyManagementException, NoSuchAlgorithmException, KeyStoreException, IOException, ParserConfigurationException,
            SAXException, org.xml.sax.SAXException {

        String url = ConfigureUtils.CloseOrder_API;
        //检测必填参数
        if(!inputObj.IsSet("out_trade_no"))
        {
            throw new WxPayException("关闭订单接口中，out_trade_no必填！");
        }

        inputObj.SetValue("appid", ConfigureUtils.getAppid());//公众账号ID
        inputObj.SetValue("mch_id", ConfigureUtils.getMchid());//商户号
        inputObj.SetValue("nonce_str",GenerateNonceStr());//随机字符串
        inputObj.SetValue("sign",inputObj.MakeSign());//签名
        String xml = inputObj.ToXml();

        HttpsRequestUtils https=new HttpsRequestUtils();
        https.setSocketTimeout(timeOut);
        String response = https.sendPost(url, xml);//调用HTTP通信接口以提交数据到API
        WXPayData result = new WXPayData();
        result.FromXml(response);


        return result;
    }


    /**
     *
     * 测速上报
     * @param  interface_url 接口URL
     * @param  inputObj 参数数组
     */
    private static void ReportCostTime(String interface_url, int timeCost, WXPayData inputObj)
    {
        //如果不需要进行上报
        if(REPORT_LEVENL == 0)
        {
            return;
        }

        //如果仅失败上报
        if(REPORT_LEVENL == 1 && inputObj.IsSet("return_code") && inputObj.GetValue("return_code").toString() == "SUCCESS" &&
                inputObj.IsSet("result_code") && inputObj.GetValue("result_code").toString() == "SUCCESS")
        {
            return;
        }

        //上报逻辑
        WXPayData data = new WXPayData();
        data.SetValue("interface_url",interface_url);
        data.SetValue("execute_time_",timeCost);
        //返回状态码
        if(inputObj.IsSet("return_code"))
        {
            data.SetValue("return_code",inputObj.GetValue("return_code"));
        }
        //返回信息
        if(inputObj.IsSet("return_msg"))
        {
            data.SetValue("return_msg",inputObj.GetValue("return_msg"));
        }
        //业务结果
        if(inputObj.IsSet("result_code"))
        {
            data.SetValue("result_code",inputObj.GetValue("result_code"));
        }
        //错误代码
        if(inputObj.IsSet("err_code"))
        {
            data.SetValue("err_code",inputObj.GetValue("err_code"));
        }
        //错误代码描述
        if(inputObj.IsSet("err_code_des"))
        {
            data.SetValue("err_code_des",inputObj.GetValue("err_code_des"));
        }
        //商户订单号
        if(inputObj.IsSet("out_trade_no"))
        {
            data.SetValue("out_trade_no",inputObj.GetValue("out_trade_no"));
        }
        //设备号
        if(inputObj.IsSet("device_info"))
        {
            data.SetValue("device_info",inputObj.GetValue("device_info"));
        }

    }


    /**
     *
     * 测速上报接口实现
     * @param  inputObj 提交给测速上报接口的参数
     * @param  timeOut 测速上报接口超时时间
     * @throws WxPayException
     * @return 成功时返回测速上报接口返回的结果，其他抛异常
     * @throws IOException
     * @throws KeyStoreException
     * @throws NoSuchAlgorithmException
     * @throws KeyManagementException
     * @throws UnrecoverableKeyException
     * @throws SAXException
     * @throws ParserConfigurationException
     */
    public static WXPayData Report(WXPayData inputObj, int timeOut) throws WxPayException, UnrecoverableKeyException,
            KeyManagementException, NoSuchAlgorithmException, KeyStoreException, IOException, ParserConfigurationException,
            SAXException, org.xml.sax.SAXException {
        String url = ConfigureUtils.REPORT_API;
        //检测必填参数
        if(!inputObj.IsSet("interface_url"))
        {
            throw new WxPayException("接口URL，缺少必填参数interface_url！");
        }
        if(!inputObj.IsSet("return_code"))
        {
            throw new WxPayException("返回状态码，缺少必填参数return_code！");
        }
        if(!inputObj.IsSet("result_code"))
        {
            throw new WxPayException("业务结果，缺少必填参数result_code！");
        }
        if(!inputObj.IsSet("user_ip"))
        {
            throw new WxPayException("访问接口IP，缺少必填参数user_ip！");
        }
        if(!inputObj.IsSet("execute_time_"))
        {
            throw new WxPayException("接口耗时，缺少必填参数execute_time_！");
        }

        inputObj.SetValue("appid", ConfigureUtils.getAppid());//公众账号ID
        inputObj.SetValue("mch_id", ConfigureUtils.getMchid());//商户号
        inputObj.SetValue("user_ip", ConfigureUtils.getIP());//终端ip
        inputObj.SetValue("time",DateTimeUtils.getCurrentDateTimeText());//商户上报时间
        inputObj.SetValue("nonce_str",GenerateNonceStr());//随机字符串
        inputObj.SetValue("sign",inputObj.MakeSign());//签名
        String xml = inputObj.ToXml();

        HttpsRequestUtils https=new HttpsRequestUtils();
        https.setSocketTimeout(timeOut);
        String response = https.sendPost(url, xml);//调用HTTP通信接口以提交数据到API
        WXPayData result = new WXPayData();
        result.FromXml(response);

        return result;
    }

    /**
     * 根据当前系统时间加随机序列来生成订单号
     * @return 订单号
     */
    public static String GenerateOutTradeNo()
    {
        Random ran = new Random();
        return String.format("{0}{1}{2}", ConfigureUtils.getMchid(), DateTimeUtils.getCurrentDateTimeText(), ran.nextInt(999));
    }

    /**
     * 生成时间戳，标准北京时间，时区为东八区，自1970年1月1日 0点0分0秒以来的秒数
     * @return 时间戳
     */
    public static String GenerateTimeStamp()
    {
        //Java中的getTime方法默认的是从1970 1 1 算起所以可以直接调用
        //date.getTime获得的是毫秒数，不是秒，所以最后的结果day应当再除以1000才对。
        return String.valueOf(Calendar.getInstance().getTimeInMillis()/1000);
    }

    /**
     * 生成随机串，随机串包含字母或数字
     * @return 随机串
     */
    public static String GenerateNonceStr()
    {
        return UUIDGeneratorUtils.getUUID(DEFAULT_CODE).toString();
    }

}
