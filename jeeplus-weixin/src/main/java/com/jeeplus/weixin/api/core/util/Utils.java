package com.jeeplus.weixin.api.core.util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by yuzp17311 on 2016/8/23.
 */
public class Utils {

    private static final char[] HEXDIGITS = "0123456789abcdef".toCharArray();

    /**
     * html转义
     * @param instr
     * @return
     */
    public static String transfer(String instr){
        if (instr == null) return "";
        return instr.replace("&", "&amp;").replace("<", "&lt;")
                .replace(">", "&gt;").replace("\"", "&quot;");
    }

    /**
     * html转义字符串还原
     * @param instr
     * @return
     */
    public static String deTransfer(String instr)
    {
        if (instr == null) return "";
        return instr.replace("&amp;", "&").replace("&lt;", "<")
                .replace("&gt;", ">").replace("&quot;", "\"");
    }


    /**
     * md5加密
     * @param encypStr 要进行编码的字符串
     * @param charset 编码格式
     * @return
     * @throws UnsupportedEncodingException
     */
    public static String md5(String encypStr, String charset)
            throws UnsupportedEncodingException {
        try {
            final byte[] md5 = MessageDigest.getInstance("MD5").digest(
                    encypStr.getBytes(charset));
            final char[] md5Chars = new char[32];
            int i = 0;
            for (final byte b : md5) {
                md5Chars[i++] = HEXDIGITS[(b >> 4) & 0xF];
                md5Chars[i++] = HEXDIGITS[b & 0xF];
            }
            return new String(md5Chars);
        } catch (NoSuchAlgorithmException nsae) {
            throw new RuntimeException(
                    "No MD5 algorithm, unable to compute MD5");
        }
    }

}
