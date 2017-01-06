package com.jeeplus.weixin.pay;

import com.alibaba.fastjson.JSON;
import com.jeeplus.weixin.fastweixin.exception.WxPayException;
import com.jeeplus.weixin.pay.util.ConfigureUtils;
import com.jeeplus.weixin.pay.util.MD5Utils;
import com.jeeplus.weixin.pay.util.PayCommonUtil;
import com.jeeplus.weixin.pay.util.XMLParserUtils;

import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.SAXException;
import java.io.IOException;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 微信支付协议接口数据类，所有的API接口通信都依赖这个数据结构，
 * 在调用接口之前先填充各个字段的值，然后进行接口通信，
 * 这样设计的好处是可扩展性强，用户可随意对协议进行更改而不用重新设计数据结构，
 * 还可以随意组合出不同的协议数据包，不用为每个协议设计一个数据包结构
 * @author:yuzp17311
 * @version:v1.0
 * @date: 2017-01-05 19:45.
 */
public class WXPayData {



    public WXPayData() {

    }

    // 采用排序的Dictionary的好处是方便对数据包进行签名，不用再签名之前再做一次排序
    private SortedMap<String, Object> m_values = new TreeMap<String, Object>();


    public void put(String key, String value) {
        SetValue(key, value);

    }
    /**
     * 设置某个字段的值
     *
     * @param key
     *            字段名
     * @param value
     *            字段值
     */
    public void SetValue(String key, Object value) {
        m_values.put(key, value);
    }

    /**
     * 根据字段名获取某个字段的值
     *
     * @param key
     *            字段名
     * @return key对应的字段值
     */
    public Object GetValue(String key) {
        Object o = m_values.get(key);
        return o;
    }

    /**
     * 判断某个字段是否已设置
     *
     * @param key
     *            字段名
     * @return 若字段key已被设置，则返回true，否则返回false
     */
    public boolean IsSet(String key) {

        Object o = m_values.get(key);
        if (null != o)
            return true;
        else
            return false;
    }

    /**
     * @将Dictionary转成xml
     * @return 经转换得到的xml串
     * @throws WxPayException
     **/
    public String ToXml() throws WxPayException {
        // 数据为空时不能转化为xml格式
        if (0 == m_values.size()) {

            throw new WxPayException("WxPayData数据为空!");
        }

        /*
         * String xml = "<xml>"; for (Map.Entry<String, Object> pair :
         * m_values.entrySet())
         *
         * { // 字段值不能为null，会影响后续流程 if (pair.getValue() == null) {
         *
         * throw new WxPayException("WxPayData内部含有值为null的字段!"); }
         *
         * if (pair.getValue() instanceof Integer) { xml += "<" + pair.getKey()
         * + ">" + pair.getValue() + "</" + pair.getKey() + ">"; } else if
         * (pair.getValue() instanceof String) { xml += "<" + pair.getKey() +
         * ">" + "<![CDATA[" + pair.getValue() + "]]></" + pair.getKey() + ">";
         * } else// 除了String和int类型不能含有其他数据类型 {
         *
         * throw new WxPayException("WxPayData字段数据类型错误!"); } } xml += "</xml>";
         */
        return PayCommonUtil.getRequestXml(m_values);
    }

    /**
     * @将xml转为WxPayData对象并返回对象内部的数据
     * @param xml
     *            待转换的xml串
     * @return 经转换得到的Dictionary
     * @throws WxPayException
     * @throws SAXException
     * @throws IOException
     * @throws ParserConfigurationException
     */
    public SortedMap<String, Object> FromXml(String xml)
            throws WxPayException, ParserConfigurationException, IOException, SAXException {
        if (xml == null) {

            throw new WxPayException("将空的xml串转换为WxPayData不合法!");
        }

        m_values = XMLParserUtils.getMapFromXML(xml);

        // 2015-06-29 错误是没有签名
        if (m_values.get("return_code") != "SUCCESS") {
            return m_values;
        }
        CheckSign();// 验证签名,不通过会抛异常

        return m_values;
    }

    /**
     * @throws WxPayException
     * @Dictionary格式转化成url参数格式 @ return url格式串, 该串不包含sign字段值
     */
    public String ToUrl() throws WxPayException {
        String buff = "";
        for (Map.Entry<String, Object> pair : m_values.entrySet()) {
            if (pair.getValue() == null) {
                throw new WxPayException("WxPayData内部含有值为null的字段!");
            }

            if (pair.getKey() != "sign" && pair.getValue().toString() != "") {
                buff += pair.getKey() + "=" + pair.getValue() + "&";
            }
        }
        buff = sideTrim(buff, "&");
        return buff;
    }

    /**
     *
     *
     * 去掉指定字符串的开头和结尾的指定字符
     *
     *
     *
     * @param stream
     *            要处理的字符串
     * @param trimstr
     *            要去掉的字符串
     * @return 处理后的字符串
     */
    public static String sideTrim(String stream, String trimstr) {
        // null或者空字符串的时候不处理
        if (stream == null || stream.length() == 0 || trimstr == null || trimstr.length() == 0) {
            return stream;
        }

        // 结束位置
        int epos = 0;

        // 正规表达式
        String regpattern = "[" + trimstr + "]*+";
        Pattern pattern = Pattern.compile(regpattern, Pattern.CASE_INSENSITIVE);

        // 去掉结尾的指定字符
        StringBuffer buffer = new StringBuffer(stream).reverse();
        Matcher matcher = pattern.matcher(buffer);
        if (matcher.lookingAt()) {
            epos = matcher.end();
            stream = new StringBuffer(buffer.substring(epos)).reverse().toString();
        }

        // 去掉开头的指定字符
        matcher = pattern.matcher(stream);
        if (matcher.lookingAt()) {
            epos = matcher.end();
            stream = stream.substring(epos);
        }

        // 返回处理后的字符串
        return stream;
    }

    /**
     * @Dictionary格式化成Json
     * @return json串数据
     */
    public String ToJson() {
        String jsonStr = JSON.toJSONString(m_values);
        return jsonStr;
    }

    /**
     * @throws WxPayException
     * @values格式化成能在Web页面上显示的结果（因为web页面上不能直接输出xml格式的字符串）
     */
    public String ToPrintStr() throws WxPayException {
        String str = "";
        for (Map.Entry<String, Object> pair : m_values.entrySet()) {
            if (pair.getValue() == null) {

                throw new WxPayException("WxPayData内部含有值为null的字段!");
            }

            str += String.format("{0}={1}<br>", pair.getKey(), pair.getValue().toString());
        }

        return str;
    }

    /**
     * @生成签名，详见签名生成算法
     * @return 签名, sign字段不参加签名
     * @throws WxPayException
     */
    public String MakeSign() throws WxPayException {
        // 转url格式
        String str = ToUrl();
        // 在String后加入API KEY
        str += "&key=" + ConfigureUtils.getKey();
        // MD5加密
        //PayCommonUtil.createSign("UTF-8", packageParams,key);
        // 所有字符转为大写
        return MD5Utils.MD5Encode(str, "UTF-8").toUpperCase();
    }

    /**
     *
     * 检测签名是否正确 正确返回true，错误抛异常
     *
     * @throws WxPayException
     */
    public boolean CheckSign() throws WxPayException {
        // 如果没有设置签名，则跳过检测
        if (!IsSet("sign")) {

            throw new WxPayException("WxPayData签名存在但不合法!");
        }
        // 如果设置了签名但是签名为空，则抛异常
        else if (GetValue("sign") == null || GetValue("sign").toString() == "") {

            throw new WxPayException("WxPayData签名存在但不合法!");
        }

        // 获取接收到的签名
        String return_sign = GetValue("sign").toString();

        // 在本地计算新的签名
        String cal_sign = MakeSign();

        if (cal_sign == return_sign) {
            return true;
        }

        throw new WxPayException("WxPayData签名验证错误!");
    }

    /**
     * @获取Dictionary
     */
    public SortedMap<String, Object> GetValues() {
        return m_values;
    }

}
