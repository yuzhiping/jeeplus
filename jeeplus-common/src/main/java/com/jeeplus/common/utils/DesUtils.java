package com.jeeplus.common.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.security.SecureRandom;

/**
 * DES加密解密类
 * Created by yuzp17311 on 2016/8/31.
 */
public class DesUtils {

    private static Logger logger= LoggerFactory.getLogger(DesUtils.class);

    public static final String defaultKey = "awujdklowdbsurtg";

    /**
     * AES加密
     * @param str 要进行加密的字符串
     * @param key 加密使用的key串
     * @return
     */
    public static String aesEncrypt(String str, String key)  {
        if (str == null || key == null)
            return null;
        try {
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, new SecretKeySpec(key.getBytes("utf-8"), "AES"));
            byte[] bytes = cipher.doFinal(str.getBytes("utf-8"));
            return new BASE64Encoder().encode(bytes);
        }catch (Exception e){
            logger.error("AES加密失败："+e.getMessage());
            throw new RuntimeException(e);
        }

    }

    /**
     * AES解密
     * @param str 要进行解密的字符串
     * @param key 加密使用的key串
     * @return
     */
    public static String aesDecrypt(String str, String key)  {
        if (str == null || key == null)
            return null;
        try {
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(Cipher.DECRYPT_MODE, new SecretKeySpec(key.getBytes("utf-8"), "AES"));
            byte[] bytes = new BASE64Decoder().decodeBuffer(str);
            bytes = cipher.doFinal(bytes);
            return new String(bytes, "utf-8");
        }catch (Exception e){
            logger.error("AES解密失败："+e.getMessage());
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) throws Exception {
        String str = aesEncrypt("yzp140103", DesUtils.defaultKey);
        System.out.println(str);
        str = aesDecrypt("yimLj5LvEgx/Noi9/GGzXw==", DesUtils.defaultKey);
        System.out.println(str);
    }


}
