package com.jeeplus.blog.common.freemarker.directive.article;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import com.jeeplus.blog.entities.BlogCategory;
import com.jeeplus.blog.service.BlogCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import freemarker.core.Environment;
import freemarker.template.ObjectWrapper;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateDirectiveModel;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;
/**
 * 
*    
* 类名称：CountCategoryDirective   
* 类描述：   博客分类统计
* 创建人：kings.chan
* 创建时间：2016-8-1 下午2:36:11   
* 修改人：   
* 修改时间：
* 项目：ROOT
* 修改备注：   
* @version    
*
 */
@Component("CountCategory")
public class CountCategoryDirective implements TemplateDirectiveModel{

	@Autowired
    private BlogCategoryService categoryService;
	
	@Override
	public void execute(Environment env, @SuppressWarnings("rawtypes") Map params, TemplateModel[] tm,
			TemplateDirectiveBody body) throws TemplateException, IOException {
		 String website_id=params.containsKey("website_id")?params.get("website_id").toString():"";//网站
	        try {
	            List<BlogCategory> lis= categoryService.countCategory(website_id);
	            env.setVariable("category_lis", ObjectWrapper.DEFAULT_WRAPPER.wrap(lis));
	            body.render(env.getOut());
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	}

}
