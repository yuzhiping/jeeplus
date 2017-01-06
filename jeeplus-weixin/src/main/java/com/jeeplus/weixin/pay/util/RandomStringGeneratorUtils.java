package com.jeeplus.weixin.pay.util;

import java.util.Random;

/**
 * 随机字符串生成工具类
 * @author:yuzp17311
 * @version:v1.0
 * @date: 2017-01-05 19:38.
 */
public class RandomStringGeneratorUtils {

    /**
     * 获取一定长度的随机字符串
     * @param length 指定字符串长度
     * @return 一定长度的字符串
     */
    public static String getRandomStringByLength(int length) {
        String base = "abcdefghijklmnopqrstuvwxyz0123456789";
        Random random = new Random();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < length; i++) {
            int number = random.nextInt(base.length());
            sb.append(base.charAt(number));
        }
        return sb.toString();
    }

}
