package com.jeeplus.admin.common.response;

import com.jeeplus.admin.common.enums.ResponseErrorEnum;
import com.jeeplus.common.utils.JacksonMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by yuzp17311 on 2016/9/8.
 */
public class ResultGenerator {

    private static final Logger logger= LoggerFactory.getLogger(ResultGenerator.class);


    /**
     * 生成响应成功的(不带正文)的结果
     *
     * @param message 成功提示信息
     * @return ResponseResult
     */
    public static ResponseResult genResult(String message) {

        ResponseResult responseResult = ResponseResult.newInstance();
        responseResult.setSuccess(true);
        responseResult.setMessage(message);

        return responseResult;
    }

    /**
     * 生成响应成功(带正文)的结果
     * @param data 结果正文
     * @param message 成功提示信息
     * @param <T>
     * @return
     * @throws IllegalAccessException
     * @throws InstantiationException
     */
    public static <T> ResponseResult<T> genResult(T data,String message) {
        ResponseResult<T> result=ResponseResult.newInstance();
        result.setSuccess(true);
        result.setData(data);
        result.setMessage(message);
        if(logger.isDebugEnabled())
            logger.debug("---->result:{}", JacksonMapper.toJsonString(data));
        return result;
    }

    /**
     * 生成响应失败的结果
     *
     * @param message 自定义错误信息
     * @return ResponseResult
     */
    public static ResponseResult genErrorResult(String message) {

        ResponseResult result = ResponseResult.newInstance();
        result.setSuccess(false);
        result.setMessage(message);

        if (logger.isDebugEnabled()) {
            logger.debug("--------> result:{}", JacksonMapper.toJsonString(result));
        }

        return result;
    }


    /**
     * 生成响应失败(带errorCode)的结果
     * @param responseErrorEnum responseErrorEnum 失败信息
     * @return
     * @throws IllegalAccessException
     * @throws InstantiationException
     */
    public static ResponseResult genErrorResult(ResponseErrorEnum responseErrorEnum)  {
        ResponseResult result = ResponseResult.newInstance();
        result.setSuccess(false);
        result.setErrorInfo(responseErrorEnum);

        if (logger.isDebugEnabled()) {
            logger.debug("--------> result:{}", JacksonMapper.toJsonString(result));
        }

        return result;
    }

}
