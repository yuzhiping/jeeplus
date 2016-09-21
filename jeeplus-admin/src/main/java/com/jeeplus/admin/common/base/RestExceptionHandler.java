package com.jeeplus.admin.common.base;

import com.jeeplus.admin.common.enums.ResponseErrorEnum;
import com.jeeplus.admin.common.response.ResponseResult;
import com.jeeplus.admin.common.response.ResultGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.UnexpectedTypeException;

/**
 * RestController全局异常处理器
 * Created by yuzp17311 on 2016/9/8.
 */
@ControllerAdvice(annotations = RestController.class)
public class RestExceptionHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(RestExceptionHandler.class);

    /**
     * 统一的rest接口异常处理器
     *
     * @param e 捕获的异常
     * @return 异常信息
     */
    @ExceptionHandler
    @ResponseBody
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    private <T> ResponseResult<T> globalExceptionHandler(Exception e) {

        LOGGER.error("--------->接口调用异常!", e);
        return ResultGenerator.genErrorResult(ResponseErrorEnum.INTERNAL_SERVER_ERROR);
    }

    /**
     * bean校验未通过异常
     *
     * @see javax.validation.Valid
     * @see org.springframework.validation.Validator
     * @see org.springframework.validation.DataBinder
     */
    @ExceptionHandler(UnexpectedTypeException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    private <T> ResponseResult<T> illegalParamsExceptionHandler(UnexpectedTypeException e) {

        LOGGER.error("--------->请求参数不合法!", e);
        return ResultGenerator.genErrorResult(ResponseErrorEnum.ILLEGAL_PARAMS);
    }

}
