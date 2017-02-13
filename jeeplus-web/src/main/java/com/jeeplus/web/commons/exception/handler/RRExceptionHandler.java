package com.jeeplus.web.commons.exception.handler;

import com.alibaba.fastjson.JSON;
import com.jeeplus.web.commons.exception.RRException;
import com.jeeplus.web.commons.R;
import org.apache.shiro.authz.AuthorizationException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 异常处理器
 * @author:yuzp17311
 * @version:v1.0
 * @date: 2016-12-11 16:31.
 */
@Component
public class RRExceptionHandler implements HandlerExceptionResolver {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public ModelAndView resolveException(HttpServletRequest request,
                                         HttpServletResponse response, Object handler, Exception ex) {
        R r = new R();
        try {
            response.setContentType("application/json;charset=utf-8");
            response.setCharacterEncoding("utf-8");

            if (ex instanceof RRException) {
                r.put("code", ((RRException) ex).getCode());
                r.put("msg", ((RRException) ex).getMessage());
            }else if(ex instanceof DuplicateKeyException){
                r = R.error("数据库中已存在该记录");
            }else if(ex instanceof AuthorizationException){
                r = R.error("没有权限，请联系管理员授权");
            }else{
                r = R.error();
            }

            //记录异常日志


            logger.error(ex.getMessage(), ex);

            String json = JSON.toJSONString(r);
            response.getWriter().print(json);
        } catch (Exception e) {
            logger.error("RRExceptionHandler 异常处理失败", e);
        }
        return new ModelAndView();
    }
}
