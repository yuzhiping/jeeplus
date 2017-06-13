package com.jeeplus.blog.common.freemarker.directive.blogsite;

import com.jeeplus.blog.controller.dto.BlogWebSiteDTO;
import com.jeeplus.blog.service.BlogWebSiteService;
import freemarker.core.Environment;
import freemarker.template.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * @author:yuzp17311
 * @version:v1.0
 * @date: 2017-05-24 14:50.
 */
@Component("WebSiteDirective")
public class WebSiteDirective implements TemplateDirectiveModel {

    @Autowired
    private BlogWebSiteService webSiteService;

    @Override
    public void execute(Environment environment, Map map, TemplateModel[] templateModels, TemplateDirectiveBody templateDirectiveBody)
            throws TemplateException, IOException {

        try {
            List<BlogWebSiteDTO> data=webSiteService.getWebSiteList();
            environment.setVariable("website_lis", ObjectWrapper.DEFAULT_WRAPPER.wrap(data));
            templateDirectiveBody.render(environment.getOut());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
