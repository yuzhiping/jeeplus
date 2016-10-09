package com.jeeplus.admin.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * 配置url映射大小写不敏感
 * @author:yuzp17311
 * @version:v1.0
 * @date: 2016-09-27 14:17.
 */
@Configuration
public class WebConfig extends WebMvcConfigurerAdapter {
    @Override
    public void configurePathMatch(PathMatchConfigurer configurer) {
        AntPathMatcher matcher = new AntPathMatcher();
        matcher.setCaseSensitive(false);
        configurer.setPathMatcher(matcher);
    }
}
