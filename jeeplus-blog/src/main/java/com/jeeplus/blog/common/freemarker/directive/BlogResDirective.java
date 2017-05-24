package com.jeeplus.blog.common.freemarker.directive;

import com.jeeplus.blog.common.Pagination;
import com.jeeplus.blog.service.BlogResService;
import freemarker.core.Environment;
import freemarker.template.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * 资源管理标签
 * @author:yuzp17311
 * @version:v1.0
 * @date: 2017-05-24 14:09.
 */
@Component("BlogRes")
public class BlogResDirective implements TemplateDirectiveModel {

    @Autowired
    private BlogResService blogResService;
    @Override
    public void execute(Environment environment, Map params, TemplateModel[] templateModels, TemplateDirectiveBody templateDirectiveBody)
            throws TemplateException, IOException {

        int page =params.containsKey("page")&&params.get("page").toString().matches("\\d+")?Integer.valueOf(params.get("page").toString()):1;
        int limit =params.containsKey("limit")&&params.get("limit").toString().matches("\\d+")?Integer.valueOf(params.get("limit").toString()):10;
        String websiteid=params.containsKey("websiteid")?params.get("websiteid").toString():"";//网站
        String resname=params.containsKey("resname")?params.get("resname").toString():"";//名称
        Map<String, Object> map = new HashMap<String, Object>();
        Pagination p=null;
        map.put("websiteid", websiteid);
        if (!resname.isEmpty()) {
            map.put("resname", resname.concat("%"));
            map.put("resKey", resname);

        }
        try {
            p=blogResService.getResList(page, limit, map);
            environment.setVariable("blogres_lis", ObjectWrapper.DEFAULT_WRAPPER.wrap(p.getData()));
            environment.setVariable("page", ObjectWrapper.DEFAULT_WRAPPER.wrap(p));
            templateDirectiveBody.render(environment.getOut());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
