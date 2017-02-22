package com.jeeplus.common.util;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

/**
 * @author:yuzp17311
 * @version:v1.0
 * @date: 2017-02-22 10:06.
 */
public class UrlUtils {
    /**
     * 得到url最后一个斜杠后面的字符串
     * @param url
     * @return
     */
    public static String getLastSlashData(String url){
        String[] strings=url.split("/");
        String keyWord=null;
        try {
            keyWord= URLDecoder.decode(strings[strings.length-1],"utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return keyWord;
    }
}
