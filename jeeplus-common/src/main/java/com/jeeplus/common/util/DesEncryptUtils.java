package com.jeeplus.common.util;


//思路： 因为   任意一个字符串，都是由若干字节表示的，每个字节实质就是一个
//              有8位的进进制数，
//      又因为   一个8位二进制数，可用两位16进制字符串表示.
//        因此   任意一个字符串可以由两位16进制字符串表示。
//          而   DES是对8位二进制数进行加密，解密。
//        所以   用DES加密解密时，可以把加密所得的8位进进制数，转成
//               两位16进制数进行保存，传输。
//    具体方法：1 把一个字符串转成8位二进制数，用DES加密，得到8位二进制数的
//                密文
//              2 然后把（由1）所得的密文转成两位十六进制字符串
//              3 解密时，把（由2)所得的两位十六进制字符串，转换成8位二进制
//                数的密文
//              4 把子3所得的密文，用DES进行解密，得到8位二进制数形式的明文，
//                并强制转换成字符串。
// 思考：为什么要通过两位16进制数字符串保存密文呢？
//       原因是：一个字符串加密后所得的8位二进制数，通常不再时字符串了，如果
//              直接把这种密文所得的8位二进制数强制转成字符串，有许多信息因为异
//              常而丢失，导制解密失败。因制要把这个8位二制数，直接以数的形式
//              保存下来，而通常是用两位十六进制数表示。

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.crypto.Cipher;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import java.security.Key;

/**
 * 字符串对称加密工具类
 * 使用DES加密与解密,可对byte[],String类型进行加密与解密 密文可使用String,byte[]存储.
 * @author:yuzp17311
 * @version:v1.0
 * @date: 2017-02-22 10:47.
 */
public class DesEncryptUtils {

    private static final Logger logger= LoggerFactory.getLogger(DesEncryptUtils.class);

    Key key;
    private static DesEncryptUtils instance = null;

    private DesEncryptUtils() {
    }

    /**
     *
     * @param keyStr
     *            密钥
     * @return
     */
    public static synchronized DesEncryptUtils getInstance(String keyStr) {
        if (null == instance) {
            instance = new DesEncryptUtils();
        }
        instance.getKey(keyStr);
        return instance;
    }

    /**
     * 根据参数生成KEY
     *
     * @param strKey
     */

    public void getKey(String strKey) {
        try {
            SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
            DESKeySpec keySpec = new DESKeySpec(strKey.getBytes());
            this.key = keyFactory.generateSecret(keySpec);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 对字符串进行加密
     *
     * @param content 进行加密的字符串
     * @return
     */
    public String encryptString(String content) {
        if(content==null)
            return null;
        try {
            return byte2hex(encryptCode(content.getBytes()));
        } catch (Exception e) {
            logger.error("对字符串{0}进行DES加密失败：{1}",content,e.getMessage());
            throw new RuntimeException(e);
        }

    }

    /**
     * 对字符串进行解密
     *
     * @param content
     * @return
     */
    public String descryptString(String content) {
        if(content==null)
            return null;
        try {
            return new String(descryptCode(hex2byte(content.getBytes())));
        } catch (Exception e) {
            logger.error("对字符串{0}进行DES解密失败：{1}",content,e.getMessage());
            throw new RuntimeException(e);
        }
    }

    /**
     * 对byte数组进行加密,以byte数组密文输出
     *
     * @param byteS
     * @return
     */
    private byte[] encryptCode(byte[] byteS) {
        if(byteS.length==0)
            return null;
        byte[] byteFina = null;
        Cipher cipher;
        try {
            cipher = Cipher.getInstance("DES");
            cipher.init(Cipher.ENCRYPT_MODE, key);
            byteFina = cipher.doFinal(byteS);
            return byteFina;
        } catch (Exception e) {
            logger.error("对byte数组进行DES加密失败：{0}",e.getMessage());
            throw new RuntimeException(e);
        } finally {
            cipher = null;
        }
    }

    /**
     * 对byte数组进行解密,以byte数组明文输出
     *
     * @param byteD
     * @return
     */
    private byte[] descryptCode(byte[] byteD) {
        if(byteD.length==0)
            return null;
        Cipher cipher;
        byte[] byteFina = null;
        try {
            cipher = Cipher.getInstance("DES");
            cipher.init(Cipher.DECRYPT_MODE, key);
            byteFina = cipher.doFinal(byteD);
        } catch (Exception e) {
            logger.error("对byte数组进行DES解密失败：{0}",e.getMessage());
            throw new RuntimeException(e);
        } finally {
            cipher = null;
        }
        return byteFina;
    }

    /**
     * 二进制转字符串
     *
     * @param b
     * @return
     */
    public static String byte2hex(byte[] b) { // 一个字节的数，
        // 转成16进制字符串
        String hs = "";
        String stmp = "";
        for (int n = 0; n < b.length; n++) {
            // 整数转成十六进制表示
            stmp = (Integer.toHexString(b[n] & 0XFF));
            if (stmp.length() == 1)
                hs = hs + "0" + stmp;
            else
                hs = hs + stmp;
        }
        return hs.toUpperCase(); // 转成大写
    }

    /**
     * 字符串转二进制
     * @param b
     * @return
     */
    public static byte[] hex2byte(byte[] b) {
        if ((b.length % 2) != 0)
            throw new IllegalArgumentException("长度不是偶数");
        byte[] b2 = new byte[b.length / 2];
        for (int n = 0; n < b.length; n += 2) {
            String item = new String(b, n, 2);
            // 两位一组，表示一个字节,把这样表示的16进制字符串，还原成一个进制字节
            b2[n / 2] = (byte) Integer.parseInt(item, 16);
        }

        return b2;
    }

}
