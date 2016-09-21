package com.jeeplus.weixin.oauth2.util;

import java.security.MessageDigest;

/**
 * Created by yuzp17311 on 2016/9/12.
 */
public class MD5Util {

    private static String byteArrayToHexString(byte [] b){
        StringBuffer sb=new StringBuffer();
        for(int i=0;i<b.length;i++)
            sb.append(byteToHexString(b[i]));
        return sb.toString();

    }

    private static String byteToHexString(byte b){
        int n=b;
        if(n<0)
            n+=256;
        int d1=n/16;
        int d2=n%16;
        return hexDigits[d1]+hexDigits[d2];
    }

    /**
     * MD5加密
     * @param origin 加密字符串
     * @param charsetname 编码方式
     * @return
     */
    public static String MD5Encode(String origin, String charsetname) {
        String resultString = null;
        try {
            resultString = new String(origin);
            MessageDigest md = MessageDigest.getInstance("MD5");
            if (charsetname == null || "".equals(charsetname))
                resultString = byteArrayToHexString(md.digest(resultString
                        .getBytes()));
            else
                resultString = byteArrayToHexString(md.digest(resultString
                        .getBytes(charsetname)));
        } catch (Exception exception) {
        }
        return resultString;
    }


    private static final String hexDigits[]={
            "0", "1", "2", "3", "4", "5",
            "6", "7", "8", "9", "a", "b", "c", "d", "e", "f"
    };

}
