package com.jeeplus.blog.common.freemarker.directive.blogsite;

import com.jeeplus.blog.controller.dto.BlogWebSiteCountInfoDTO;
import com.jeeplus.blog.service.BlogReportService;
import freemarker.core.Environment;
import freemarker.template.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Map;

/**
 * @author:yuzp17311
 * @version:v1.0
 * @date: 2017-05-24 14:53.
 */
@Component("WebSiteCountInfo")
public class WebSiteCountInfoDirective implements TemplateDirectiveModel {

    @Autowired
    private BlogReportService reportService;

    @Override
    public void execute(Environment environment, Map params, TemplateModel[] templateModels, TemplateDirectiveBody templateDirectiveBody)
            throws TemplateException, IOException {

        String siteid=params.containsKey("siteid")?params.get("siteid").toString():null;//标签
        //lableName
        try {
            BlogWebSiteCountInfoDTO vo =reportService.websiteCountInfo(siteid);
            environment.setVariable("WebSiteCountInfoVo", ObjectWrapper.DEFAULT_WRAPPER.wrap(vo));
            templateDirectiveBody.render(environment.getOut());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
