package com.jeeplus.weixin.fastweixin.api;

import com.jeeplus.weixin.fastweixin.api.config.ApiConfig;
import com.jeeplus.weixin.fastweixin.api.entity.Industry;
import com.jeeplus.weixin.fastweixin.api.entity.TemplateMsg;
import com.jeeplus.weixin.fastweixin.api.enums.ResultType;
import com.jeeplus.weixin.fastweixin.api.response.AddTemplateResponse;
import com.jeeplus.weixin.fastweixin.api.response.BaseResponse;
import com.jeeplus.weixin.fastweixin.api.response.PrivateTemplate;
import com.jeeplus.weixin.fastweixin.api.response.SendTemplateResponse;
import com.jeeplus.weixin.fastweixin.util.BeanUtils;
import com.jeeplus.weixin.fastweixin.util.JSONUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * 模版消息 api
 */
public class TemplateMsgAPI extends BaseAPI {
    private static final Logger LOG = LoggerFactory.getLogger(CustomAPI.class);

    public TemplateMsgAPI(ApiConfig config) {
        super(config);
    }

    /**
     * 设置行业
     *
     * @param industry 行业参数
     * @return 操作结果
     */
    public ResultType setIndustry(Industry industry) {
        LOG.debug("设置行业......");
        BeanUtils.requireNonNull(industry, "行业对象为空");
        String url = BASE_API_URL + "cgi-bin/template/api_set_industry?access_token=#";
        BaseResponse response = executePost(url, industry.toJsonString());
        return ResultType.get(response.getErrcode());
    }

    /**
     * 添加模版
     *
     * @param shortTemplateId 模版短id
     * @return 操作结果
     */
    public AddTemplateResponse addTemplate(String shortTemplateId) {
        LOG.debug("添加模版......");
        BeanUtils.requireNonNull(shortTemplateId, "短模版id必填");
        String url = BASE_API_URL + "cgi-bin/template/api_add_template?access_token=#";
        Map<String, String> params = new HashMap<String, String>();
        params.put("template_id_short", shortTemplateId);
        BaseResponse r = executePost(url, JSONUtils.toJson(params));
        String resultJson = isSuccess(r.getErrcode()) ? r.getErrmsg() : r.toJsonString();
        AddTemplateResponse result = JSONUtils.toBean(resultJson, AddTemplateResponse.class);
        return result;
    }

    /**
     * 发送模版消息
     *
     * @param msg 消息
     * @return 发送结果
     */
    public SendTemplateResponse send(TemplateMsg msg) {

        LOG.debug("获取模版id......");
        BeanUtils.requireNonNull(msg.getTouser(), "openid is null");
        BeanUtils.requireNonNull(msg.getTemplateId(), "template_id is null");
        BeanUtils.requireNonNull(msg.getData(), "data is null");
        BeanUtils.requireNonNull(msg.getTopcolor(), "top color is null");
        //BeanUtils.requireNonNull(msg.getUrl(), "url is null");
        String url = BASE_API_URL + "cgi-bin/message/template/send?access_token=#";
        BaseResponse r = executePost(url, msg.toJsonString());
        String resultJson = isSuccess(r.getErrcode()) ? r.getErrmsg() : r.toJsonString();
        SendTemplateResponse result = JSONUtils.toBean(resultJson, SendTemplateResponse.class);
        return result;
    }

    /**
     * 获取已添加至帐号下所有模板列表
     *
     * @return 所有模板
     */
    public PrivateTemplate[] getAllPrivateTemplate() {
        LOG.debug("获取已添加至帐号下所有模板列表......");
        String url = BASE_API_URL + "cgi-bin/template/get_all_private_template?access_token=#";
        BaseResponse r = executeGet(url);
        String resultJson = isSuccess(r.getErrcode()) ? r.getErrmsg() : r.toJsonString();
        PrivateTemplate[] templates = JSONUtils.toBean(JSONUtils.toJson(JSONUtils.getJSONFromString(resultJson).get("template_list")), PrivateTemplate[].class);
        return templates;
    }

    /**
     * 删除模板
     *
     * @param templateId 模板ID
     * @return 删除结果
     */
    public BaseResponse delTemplate(String templateId) {
        LOG.debug("删除模板......");
        BeanUtils.requireNonNull(templateId, "templateId is null");
        String url = BASE_API_URL + "cgi-bin/template/del_private_template?access_token=#";
        Map<String, String> map = new HashMap<String, String>();
        map.put("template_id", templateId);
        BaseResponse r = executePost(url, JSONUtils.toJson(map));
        String resultJson = isSuccess(r.getErrcode()) ? r.getErrmsg() : r.toJsonString();
        return JSONUtils.toBean(resultJson, BaseResponse.class);
    }


}
