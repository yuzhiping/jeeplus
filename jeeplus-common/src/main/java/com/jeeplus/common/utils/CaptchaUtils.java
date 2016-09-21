package com.jeeplus.common.utils;


import com.google.code.kaptcha.Constants;

import javax.servlet.http.HttpServletRequest;

/**
 * 验证码工具类
 * Created by yuzp17311 on 2016/8/11.
 */
public class CaptchaUtils {
    public static boolean verify(HttpServletRequest request) {
        //从session中取出servlet生成的验证码text值
        String expected = (String) request.getSession().getAttribute(Constants.KAPTCHA_SESSION_KEY);
        //获取用户页面输入的验证码
        String received = request.getParameter("kaptcha");
        return received != null && received.equalsIgnoreCase(expected);
    }

}
