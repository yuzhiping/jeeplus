package com.jeeplus.common.exception.model;

/**
 * Created by yuzp17311 on 2016/8/22.
 */
public class GlobalException extends Exception {
    //异常信息
    public String message;

    public GlobalException(String message){
        super(message);
        this.message=message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
