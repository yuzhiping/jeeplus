package com.jeeplus.common.exception;

import com.jeeplus.common.exception.model.GlobalException;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by yuzp17311 on 2016/8/22.
 */
public class GlobalExceptionResolver implements HandlerExceptionResolver {
    @Override
    public ModelAndView resolveException(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception ex) {
        // handler就是处理器适配器要执行的Handler对象，只有（method）

        // 解析出异常类型

        // 如果该异常是系统自定义的异常，直接取出异常，在错误页面展示
        GlobalException customException = null;
        if (ex instanceof GlobalException) {
            customException = (GlobalException) ex;
        } else {
            // 如果该异常不是系统自定义的异常，构造一个自定义的异常信息（信息为"未知错误"）
            customException = new GlobalException("未知错误");
        }

        // 错误信息

        String message = customException.getMessage();

        ModelAndView modelAndView = new ModelAndView();

        modelAndView.addObject("message", message);
        modelAndView.setViewName("error");

        return modelAndView;
    }
}
