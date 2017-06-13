package com.jeeplus.blog.common.freemarker.directive.home;

import com.jeeplus.blog.common.Pagination;
import com.jeeplus.blog.controller.dto.BlogArticleDTO;
import com.jeeplus.blog.service.BlogArticleService;
import freemarker.core.Environment;
import freemarker.template.*;
import org.jsoup.Jsoup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author:yuzp17311
 * @version:v1.0
 * @date: 2017-05-24 14:35.
 */
@Component("HomeArticle")
public class HomeArticleDirective implements TemplateDirectiveModel {

    @Autowired
    private BlogArticleService articleService;

    @Override
    public void execute(Environment environment, Map params, TemplateModel[] templateModels, TemplateDirectiveBody templateDirectiveBody)
            throws TemplateException, IOException {

        List<BlogArticleDTO> lis=null;
        try {
            int page =params.containsKey("page")&&params.get("page").toString().matches("\\d+")?Integer.valueOf(params.get("page").toString()):1;
            int limit =params.containsKey("limit")&&params.get("limit").toString().matches("\\d+")?Integer.valueOf(params.get("limit").toString()):10;
            String category=params.containsKey("category")?params.get("category").toString():"";//类型
            String fulltext=params.containsKey("fulltext")?params.get("fulltext").toString():"";//全文检索


            Map<String, Object> map = new HashMap<String, Object>();
            Pagination p=null;
            if (!category.isEmpty()) {
                map.put("category", params.get("category").toString());
            } if (!fulltext.isEmpty()) {
                //全文检索
                p=articleService.getHomeFullTextSearch(page, limit, fulltext, "articleTitle","articleContent","id");
            }else{
                p=articleService.getHomeArticleList(page,limit, map);
            }
            if (fulltext.isEmpty()) {
                lis=(List<BlogArticleDTO>) p.getData();
                String summary=null;
                for (BlogArticleDTO article : lis) {
                    if (null==article.getArticleSummary()||article.getArticleSummary().isEmpty()) {
                        summary=Jsoup.parse(article.getArticleContent()).text();
                        summary=summary.length()>300? summary.substring(0,300):summary;
                    }else {
                        summary= Jsoup.parse(article.getArticleSummary()).text();
                    }
                    article.setArticleSummary(summary);
                }
                p.setData(lis);
            }
            environment.setVariable("article_lis", ObjectWrapper.DEFAULT_WRAPPER.wrap(p.getData()));
            environment.setVariable("page", ObjectWrapper.DEFAULT_WRAPPER.wrap(p));
            templateDirectiveBody.render(environment.getOut());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
