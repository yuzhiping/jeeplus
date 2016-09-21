package com.jeeplus.weixin.api.core.exception;

/**
 * 微信http请求异常信息
 * Created by yuzp17311 on 2016/8/22.
 */
public class WexinReqException extends Exception {
    private static final long serialVersionUID = 1L;

    public WexinReqException(String message){
        super(message);
    }

    public WexinReqException(Throwable cause)
    {
        super(cause);
    }

    public WexinReqException(String message,Throwable cause)
    {
        super(message,cause);
    }
}
