package com.jeeplus.common.exception;

/**
 * Created by yuzp17311 on 2016/9/12.
 */
public class BusinessException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public BusinessException(String message){
        super(message);
    }

    public BusinessException(Throwable cause)
    {
        super(cause);
    }

    public BusinessException(String message,Throwable cause)
    {
        super(message,cause);
    }
}
