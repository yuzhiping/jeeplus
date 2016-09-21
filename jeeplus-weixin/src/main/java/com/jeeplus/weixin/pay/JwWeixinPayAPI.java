package com.jeeplus.weixin.pay;

import com.jeeplus.weixin.api.core.util.Utils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.UnsupportedEncodingException;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

/**
 * Created by yuzp17311 on 2016/8/23.
 */
public class JwWeixinPayAPI {

    private static Logger logger= LoggerFactory.getLogger(JwWeixinPayAPI.class);

    /**
     * 生成签名
     * 签名在线验证工具：
     * http://mch.weixin.qq.com/wiki/tools/signverify/
     * @param treeMap 参与签名生成的参数列表
     * @param partnerKey 商家私钥
     * @return
     */
    public static String sign(TreeMap<String,String> treeMap,String partnerKey){
        String sign=null;
        StringBuilder sb=new StringBuilder();
        Iterator iterator=treeMap.entrySet().iterator();
        while (iterator.hasNext()){
            Map.Entry entry=(Map.Entry)iterator.next();
            if(StringUtils.isEmpty(entry.getValue().toString())|| //参数的值为空不参与签名；
                    null==entry.getValue().toString())
                continue;
            if("sign".equals(entry.getKey())) // 参数中为签名的项，不参加计算
                continue;
            sb.append(entry.getKey().toString()).append("=").
                    append(entry.getValue().toString()).append("&");
            sb.append("key=").append(partnerKey);
            String stringSignTemp = sb.toString();
            try {
                sign= Utils.md5(stringSignTemp,"UTF-8").toUpperCase();//对stringSignTemp进行MD5运算，再将得到的字符串所有字符转换为大写，得到sign值signValue。

            }catch (UnsupportedEncodingException e){
                logger.error("验证签名出错。");
            }

        }
        return  sign;

    }

}
