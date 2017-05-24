package com.jeeplus.blog.web.interceptor;

import com.jeeplus.blog.common.service.impl.CommonServiceImpl;
import com.jeeplus.blog.service.BlogArticleService;
import com.jeeplus.blog.service.BlogWebSiteService;
import com.jeeplus.blog.service.system.SysUrlValidationService;
import com.jeeplus.blog.util.freemarker.TemplateStaticUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.*;
import java.io.IOException;

/**
 * 博客系统url映射跳转核心过滤器
 * @author:yuzp17311
 * @version:v1.0
 * @date: 2017-05-24 11:01.
 */
@Component("BlogFilter")
public class BlogFilter implements Filter {

    @Autowired
    private BlogWebSiteService blogWebSiteService;
    @Autowired
    private BlogArticleService blogArticleService;
    @Resource(name="CommonServiceImpl")
    private CommonServiceImpl commonService;
    @Autowired
    private SysUrlValidationService sysUrlValidationService;
    //默认跳转博客
    @Value("${app.default.blog.keyword}")
    private String defaultBolg;
    @Autowired
    private TemplateStaticUtils tsu;
    //runtime declare
    private String blogname;//当前博客名
    @Value("${sina_weibo_login_url}")
    private String sinaWeiBoLoginUrl;
    @Autowired
    private SkinTemplateVariable skinVariable;
    private static Logger log = LoggerFactory.getLogger(BlogFilter.class);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

    }

    @Override
    public void destroy() {

    }
}
