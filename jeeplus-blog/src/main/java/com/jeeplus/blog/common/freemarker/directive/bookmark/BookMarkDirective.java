package com.jeeplus.blog.common.freemarker.directive.bookmark;

import com.jeeplus.blog.common.Pagination;
import com.jeeplus.blog.service.BlogBookMarkService;
import freemarker.core.Environment;
import freemarker.template.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author:yuzp17311
 * @version:v1.0
 * @date: 2017-05-24 14:41.
 */
@Component("BookMark")
public class BookMarkDirective implements TemplateDirectiveModel {

    @Autowired
    private BlogBookMarkService bookMarkService;

    @Override
    public void execute(Environment environment, Map params, TemplateModel[] templateModels, TemplateDirectiveBody templateDirectiveBody)
            throws TemplateException, IOException {

        int page =params.containsKey("page")&&params.get("page").toString().matches("\\d+")?Integer.valueOf(params.get("page").toString()):1;
        int limit =params.containsKey("limit")&&params.get("limit").toString().matches("\\d+")?Integer.valueOf(params.get("limit").toString()):10;
        String title=params.containsKey("title")?params.get("title").toString():"";//标题
        Integer folder=params.containsKey("folder")&&params.get("folder").toString().matches("\\d+")?Integer.valueOf(params.get("folder").toString()):0;//类型
        String website_id=params.containsKey("website_id")?params.get("website_id").toString():"";//网站
        String disablepage=params.containsKey("disablepage")?params.get("disablepage").toString():"";//是否设置分布信息
        String orderby=params.containsKey("orderby")?params.get("orderby").toString():"";//排序
        Map<String, Object> map =new HashMap<String, Object>();
        if (!title.isEmpty()) {
            map.put("title", "%"+title+"%");
        }
        if (folder!=0) {
            map.put("folder", folder);
        }
        if (!orderby.isEmpty()) {
            map.put("orderby", orderby);
        }
        map.put("websiteid", website_id);
        Pagination p=null;
        try {
            p= bookMarkService.getBookMarks(website_id, map, limit, page);
            environment.setVariable("bookmark_lis", ObjectWrapper.DEFAULT_WRAPPER.wrap(p.getData()));
            //是否设置分布信息
            if (disablepage.isEmpty()) {
                environment.setVariable("page", ObjectWrapper.DEFAULT_WRAPPER.wrap(p));
            }
            templateDirectiveBody.render(environment.getOut());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
