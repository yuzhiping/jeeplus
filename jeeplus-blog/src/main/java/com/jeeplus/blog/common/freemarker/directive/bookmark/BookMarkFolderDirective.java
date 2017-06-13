package com.jeeplus.blog.common.freemarker.directive.bookmark;

import com.jeeplus.blog.entities.BlogBookmarksFolder;
import com.jeeplus.blog.service.BlogBookMarkService;
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
 * @date: 2017-05-24 14:45.
 */
@Component("BookMarkFolderDirective")
public class BookMarkFolderDirective implements TemplateDirectiveModel {

    @Autowired
    private BlogBookMarkService bookMarkService;

    @Override
    public void execute(Environment environment, Map params, TemplateModel[] templateModels, TemplateDirectiveBody templateDirectiveBody)
            throws TemplateException, IOException {

        String websiteid=params.containsKey("websiteid")?params.get("websiteid").toString():"";//网站ID
        List<BlogBookmarksFolder> lis=null;
        try {
            lis= bookMarkService.getAllBookMarksFolder(websiteid);
            environment.setVariable("bookmarkfolder_lis", ObjectWrapper.DEFAULT_WRAPPER.wrap(lis));
            templateDirectiveBody.render(environment.getOut());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
