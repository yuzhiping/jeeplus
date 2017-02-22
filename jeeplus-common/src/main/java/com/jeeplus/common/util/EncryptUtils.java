package com.jeeplus.common.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


/**
 * 加密解密工具类
 * Created by yuzp17311 on 2016/8/31.
 */
public class EncryptUtils {

    private static Logger logger= LoggerFactory.getLogger(EncryptUtils.class);

    public static final String defaultKey = "awujdklowdbsurtg";


    /**
     * SHA1
     * @param decript
     * @return
     */
    public static String SHA1(String decript){
        if(decript==null)
            return null;
        try{
            MessageDigest digest=MessageDigest.getInstance("SHA-1");
            digest.update(decript.getBytes());
            byte[] messageDigest=digest.digest();
            StringBuffer hexString=new StringBuffer();
            // 字节数组转换为十六进制数
            for (int i=0;i<messageDigest.length;i++){
                String shaHex=Integer.toHexString(messageDigest[i]&0xFF);
                if(shaHex.length()<2){
                    hexString.append(0);
                }
                hexString.append(shaHex);
            }
            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            logger.error("SHA1加密失败："+e.getMessage());
            throw new RuntimeException(e);
        }
    }

    /**
     * SHA
     * @param decript
     * @return
     */
    public static String SHA(String decript) {
        if(decript==null)
            return null;
        try {
            MessageDigest digest = MessageDigest
                    .getInstance("SHA");
            digest.update(decript.getBytes());
            byte messageDigest[] = digest.digest();
            StringBuffer hexString = new StringBuffer();
            // 字节数组转换为 十六进制 数
            for (int i = 0; i < messageDigest.length; i++) {
                String shaHex = Integer.toHexString(messageDigest[i] & 0xFF);
                if (shaHex.length() < 2) {
                    hexString.append(0);
                }
                hexString.append(shaHex);
            }
            return hexString.toString();

        } catch (NoSuchAlgorithmException e) {
            logger.error("SHA加密失败："+e.getMessage());
            throw new RuntimeException(e);
        }
    }

    /**
     * MD5加密
     *
     * @param input 需要加密的字符串
     * @return
     */
    public static String MD5(String input) {
        if(input==null)
            return null;
        try {
            // 获得MD5摘要算法的 MessageDigest 对象
            MessageDigest mdInst = MessageDigest.getInstance("MD5");
            // 使用指定的字节更新摘要
            mdInst.update(input.getBytes());
            // 获得密文
            byte[] md = mdInst.digest();
            // 把密文转换成十六进制的字符串形式
            StringBuffer hexString = new StringBuffer();
            // 字节数组转换为 十六进制 数
            for (int i = 0; i < md.length; i++) {
                String shaHex = Integer.toHexString(md[i] & 0xFF);
                if (shaHex.length() < 2) {
                    hexString.append(0);
                }
                hexString.append(shaHex);
            }
            return hexString.toString().toUpperCase();
        } catch (NoSuchAlgorithmException e) {
            logger.error("MD5加密失败："+e.getMessage());
            throw new RuntimeException(e);
        }
    }




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
        String str = aesEncrypt("1091216604", EncryptUtils.defaultKey);
        System.out.println(str);
        str = aesDecrypt("yimLj5LvEgx/Noi9/GGzXw==", EncryptUtils.defaultKey);
        System.out.println(str);
    }


}
