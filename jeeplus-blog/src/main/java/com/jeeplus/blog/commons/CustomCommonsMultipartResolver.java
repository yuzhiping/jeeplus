package com.jeeplus.blog.commons;

import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import javax.servlet.http.HttpServletRequest;

/**
 * 自定义的文件上传解析器，为使用ueditor上传的文件放行
 * @author:yuzp17311
 * @version:v1.0
 * @date: 2017-02-10 17:28.
 */
public class CustomCommonsMultipartResolver extends CommonsMultipartResolver {

    @Override
    public boolean isMultipart(HttpServletRequest request){
        String uri = request.getRequestURI();
        System.out.println(uri);
        //过滤使用百度UEditor的URI
        if (uri.indexOf("ueditor/config") > 0) {
            System.out.println("CustomCommonsMultipartResolver 放行");
            return false;
        }
        System.out.println("CustomCommonsMultipartResolver 拦截");
        return super.isMultipart(request);
    }

}
