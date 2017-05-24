package com.jeeplus.blog.util.freemarker.directive;

import com.jeeplus.common.util.StringUtils;
import freemarker.core.Environment;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateDirectiveModel;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Map;

/**
 * 根据字母和中文自动计算返回指定长度的字符
 * @author:yuzp17311
 * @version:v1.0
 * @date: 2017-05-24 14:28.
 */
@Component("SubString")
public class SubStringDirectiveUtils implements TemplateDirectiveModel {
    @Override
    public void execute(Environment environment, Map params, TemplateModel[] templateModels, TemplateDirectiveBody templateDirectiveBody)
            throws TemplateException, IOException {
        String value=params.containsKey("value")?params.get("value").toString():"";//传入的字符串
        int limit =params.containsKey("limit")&&params.get("limit").toString().matches("\\d+")?Integer.valueOf(params.get("limit").toString()):0;
        String defval=params.containsKey("defval")?params.get("defval").toString():"";
        String overflow=params.containsKey("overflow")?params.get("overflow").toString():"";//超出指定的长度 在后面追加指定的字符串
        if (limit>0&&!value.isEmpty()) {
            environment.getOut().write(StringUtils.getLengthOfSub(value, limit,overflow));
        }
        if (!defval.isEmpty()&&value.isEmpty()) {
            environment.getOut().write(defval);
        }
    }
}
