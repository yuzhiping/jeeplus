package com.jeeplus.weixin.oauth2.util;

import com.jeeplus.common.exception.BusinessException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * 加签、验签工具
 * Created by yuzp17311 on 2016/9/12.
 */
public abstract class SignatureUtil {
    private static final Logger logger= LoggerFactory.getLogger(SignatureUtil.class);


    /**
     * 加签,MD5.
     * @param paramMap 参数Map,不包含商户秘钥且顺序确定
     * @param key  商户秘钥
     * @return  签名串
     */
    public static String sign(Map<String, String> paramMap, String key) {
        if(key == null){
            throw new BusinessException("key不能为空");
        }
        String sign = createSign(paramMap,key);
        return sign;
    }

    /**
     * 创建md5摘要,规则是:按参数名称a-z排序,遇到空值的参数不参加签名。
     */
    private static String createSign(Map<String, String> paramMap, String key) {
        StringBuffer sb = new StringBuffer();
        SortedMap<String,String> sort=new TreeMap<String,String>(paramMap);
        Set<Map.Entry<String, String>> es = sort.entrySet();
        Iterator<Map.Entry<String, String>> it = es.iterator();
        while (it.hasNext()) {
            @SuppressWarnings("rawtypes")
            Map.Entry entry = (Map.Entry) it.next();
            String k = (String) entry.getKey();
            String v = (String) entry.getValue();
            if (null != v && !"".equals(v)&& !"null".equals(v) && !"sign".equals(k) && !"key".equals(k)) {
                sb.append(k + "=" + v + "&");
            }
        }
        sb.append("key=" + key);
        logger.info("HMAC source:{}", new Object[] { sb.toString() } );
        String sign = MD5Util.MD5Encode(sb.toString(), "UTF-8").toUpperCase();
        logger.info("HMAC:{}", new Object[] { sign } );
        return sign;
    }

    /**
     * 验签, 仅支持MD5.
     * @param paramMap 参数Map,不包含商户秘钥且顺序确定
     * @param key  商户秘钥
     * @param sign     签名串
     * @return         验签结果
     */
    public static boolean checkSign(Map<String, String> paramMap, String key, String sign) {
        if(key == null){
            throw new BusinessException("key不能为空");
        }
        if(sign == null){
            throw new BusinessException("需要验签的字符为空");
        }

        return sign.equals(sign(paramMap,key));
    }


    /**
     * 通过request获取签名Map
     * @param request
     * @return
     */
    public static Map<String,String> getSignMap(HttpServletRequest request){
        Map<String,String>  paramMap = new HashMap<String, String>();
        Map<String, String[]> map = request.getParameterMap();
        Set<Map.Entry<String, String[]>> es = map.entrySet();
        Iterator<Map.Entry<String, String[]>> it = es.iterator();
        while (it.hasNext()) {
            @SuppressWarnings("rawtypes")
            Map.Entry entry = (Map.Entry) it.next();
            String k = (String) entry.getKey();
            Object ov =  entry.getValue();
            String v="";
            if(ov instanceof String[]){
                String[] value=(String[])ov;
                v= value[0];
            }else{
                v=ov.toString();
            }
            paramMap.put(k, v);
        }
        return paramMap;
    }

    /**
     * 通过request获取签名Map
     * @param url
     * @return
     */
    public static Map<String,String> getSignMap(String url){
        Map<String,String>  paramMap = new HashMap<String, String>();
        url = url.substring(url.indexOf("?")+1);
        String[] params = url.split("&");
        for(int i=0;i<params.length;i++){
            String param = params[i];
            if(param.indexOf("=")!=-1){
                String[] values = param.split("=");
                paramMap.put(values[0], values[1]);
            }
        }
        return paramMap;
    }

}
