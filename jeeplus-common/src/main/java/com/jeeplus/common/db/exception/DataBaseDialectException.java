package com.jeeplus.common.db.exception;

/**
 * 数据方言异常
 * @author:yuzp17311
 * @version:v1.0
 * @date: 2017-06-28 20:53.
 */
public class DataBaseDialectException extends java.lang.RuntimeException {

    public DataBaseDialectException(String message){
        super(message);
    }
    public DataBaseDialectException(Throwable root) {
        super(root);

    }
}
