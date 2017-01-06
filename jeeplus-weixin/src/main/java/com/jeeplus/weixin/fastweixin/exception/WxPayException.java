package com.jeeplus.weixin.fastweixin.exception;

/**
 * @author:yuzp17311
 * @version:v1.0
 * @date: 2017-01-05 20:00.
 */
public class WxPayException extends RuntimeException {

    public WxPayException(){
        super();
    }

    public WxPayException(String message){
        super(message);
    }

    public WxPayException(String message,Throwable cause){
        super(message,cause);
    }
    public WxPayException(Throwable cause){
        super(cause);
    }
}
