package com.jeeplus.blog.util.freemarker.directive;

import com.jeeplus.blog.util.DateUtils;
import freemarker.core.Environment;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateDirectiveModel;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Map;

/**
 * 返回人性化时间差
 * @author:yuzp17311
 * @version:v1.0
 * @date: 2017-05-24 14:25.
 */
@Component("TimeSummary")
public class TimeSummaryDirectiveUtils implements TemplateDirectiveModel {
    @Override
    public void execute(Environment environment, Map params, TemplateModel[] templateModels, TemplateDirectiveBody templateDirectiveBody)
            throws TemplateException, IOException {

        String time=params.containsKey("time")?params.get("time").toString():"";//传入的字符串
        if (time.isEmpty()) {
            environment.getOut().write("");
        }else{
            environment.getOut().write(DateUtils.timeSummary(time));
        }
    }
}
