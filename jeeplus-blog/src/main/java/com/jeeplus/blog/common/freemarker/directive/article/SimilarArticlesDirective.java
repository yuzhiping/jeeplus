package com.jeeplus.blog.common.freemarker.directive.article;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import com.jeeplus.blog.controller.dto.BlogArticleDTO;
import com.jeeplus.blog.service.pub.impl.PubBlogServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


import freemarker.core.Environment;
import freemarker.template.ObjectWrapper;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateDirectiveModel;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;
/**
 * 相似文章
 * @author kingschan
 *
 */
@Component("SimilarArticles")
public class SimilarArticlesDirective implements TemplateDirectiveModel {

	@Autowired
	private PubBlogServiceImpl blogService;
	@Override
	 public void execute(Environment env, @SuppressWarnings("rawtypes") Map params, TemplateModel[] tm, TemplateDirectiveBody body) throws TemplateException, IOException {
		int limit =params.containsKey("limit")&&params.get("limit").toString().matches("\\d+")?Integer.valueOf(params.get("limit").toString()):10;
        String keyword=params.containsKey("keyword")?params.get("keyword").toString():"";//日期
        String website=params.containsKey("website")?params.get("website").toString():"";//网站
        List<BlogArticleDTO> lis = null;
		try {
			lis = blogService.similarArticles(website, keyword, limit);
		} catch (Exception e) {
			e.printStackTrace();
		}
        env.setVariable("SimilarArticles", ObjectWrapper.DEFAULT_WRAPPER.wrap(lis));
        body.render(env.getOut());
	}

}
