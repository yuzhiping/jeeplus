package com.jeeplus.weixin.api.wxsendmsg;

import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.util.JSONUtils;

import com.jeeplus.weixin.api.core.exception.WexinReqException;
import com.jeeplus.weixin.api.core.req.WeiXinReqService;
import com.jeeplus.weixin.api.core.req.model.message.AutoReplyRuleGet;
import com.jeeplus.weixin.api.core.util.WeiXinConstant;
import com.jeeplus.weixin.api.extend.CustomJsonConfig;
import com.jeeplus.weixin.api.wxsendmsg.model.WxArticleConfig;
import com.jeeplus.weixin.api.wxsendmsg.model.auto.AutoReplyInfoRule;
import com.jeeplus.weixin.api.wxsendmsg.model.auto.KeyWordAutoReplyInfo;
import com.jeeplus.weixin.api.wxsendmsg.model.auto.KeywordListInfo;
import com.jeeplus.weixin.api.wxsendmsg.model.auto.ReplyListInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 获取自动回复规则
 * http://mp.weixin.qq.com/wiki/8/806878e1fc2b9e9aa618ae33896b04c4.html
 * Created by yuzp17311 on 2016/8/22.
 */
public class JwGetAutoReplyRuleAPI {
	private static Logger logger = LoggerFactory.getLogger(JwGetAutoReplyRuleAPI.class);
	
	/**
	 * 获取自动回复规则
	 * @param access_token
	 * @return
	 */
	public static AutoReplyInfoRule getAutoReplyInfoRule(String access_token) throws WexinReqException{
		AutoReplyRuleGet arr = new AutoReplyRuleGet();
		arr.setAccess_token(access_token);
		JSONObject result = WeiXinReqService.getInstance().doWeinxinReqJson(arr);
		Object error = result.get(WeiXinConstant.RETURN_ERROR_INFO_CODE);
		
		AutoReplyInfoRule autoReplyInfoRule = (AutoReplyInfoRule) JSONObject.toBean(result,new CustomJsonConfig(AutoReplyInfoRule.class, "keyword_autoreply_info"));
		JSONObject keywordAutoReplyInfoJsonObj = result.getJSONObject("keyword_autoreply_info");
		if(keywordAutoReplyInfoJsonObj!=null && !JSONUtils.isNull(keywordAutoReplyInfoJsonObj)){
			/**关键词自动回复的信息 */
			JSONArray keywordAutoReplyInfos =  keywordAutoReplyInfoJsonObj.getJSONArray("list");
			if(keywordAutoReplyInfos!=null){
				List<KeyWordAutoReplyInfo> listKeyWordAutoReplyInfo = new ArrayList<KeyWordAutoReplyInfo>();
				for(int i=0;i<keywordAutoReplyInfos.size();i++){
					KeyWordAutoReplyInfo keyWordAutoReplyInfo = (KeyWordAutoReplyInfo) JSONObject.toBean(keywordAutoReplyInfos.getJSONObject(i),new CustomJsonConfig(KeyWordAutoReplyInfo.class, new String[]{"keyword_list_info","reply_list_info"}));
					/**处理关键词列表 */
					JSONArray keywordListInfos = keywordAutoReplyInfos.getJSONObject(i).getJSONArray("keyword_list_info");
					if(keywordListInfos!=null){
						List<KeywordListInfo> listKeywordListInfo = new ArrayList<KeywordListInfo>();
						for(int j=0;j<keywordListInfos.size();j++){
							KeywordListInfo keywordListInfo = (KeywordListInfo) JSONObject.toBean(keywordListInfos.getJSONObject(j),KeywordListInfo.class);
							listKeywordListInfo.add(keywordListInfo);
						}
						keyWordAutoReplyInfo.setKeyword_list_info(listKeywordListInfo);
					}
					
					/**处理关键字回复信息 */
					JSONArray replyListInfos = keywordAutoReplyInfos.getJSONObject(i).getJSONArray("reply_list_info");
					if(replyListInfos!=null){
						List<ReplyListInfo> listReplyListInfo = new ArrayList<ReplyListInfo>();
						for(int j=0;j<replyListInfos.size();j++){
							ReplyListInfo replyListInfo = (ReplyListInfo) JSONObject.toBean(keywordListInfos.getJSONObject(j),new CustomJsonConfig(ReplyListInfo.class, "news_info"));
							/**处理关键字回复相关图文消息 */
							JSONObject newsInfoJsonObj = replyListInfos.getJSONObject(j).getJSONObject("news_info");
							if(newsInfoJsonObj!=null && !JSONUtils.isNull(newsInfoJsonObj)){
								JSONArray newsInfos = newsInfoJsonObj.getJSONArray("list");
								List<WxArticleConfig> listNewsInfo = new ArrayList<WxArticleConfig>();
								for (int k = 0; k < newsInfos.size(); k++) {
									WxArticleConfig wxArticleConfig = (WxArticleConfig) JSONObject.toBean(newsInfos.getJSONObject(k), WxArticleConfig.class);
									listNewsInfo.add(wxArticleConfig);
								}
								replyListInfo.setNews_info(listNewsInfo);
							}
							listReplyListInfo.add(replyListInfo);
						}
						keyWordAutoReplyInfo.setReply_list_info(listReplyListInfo);
					}
					
					listKeyWordAutoReplyInfo.add(keyWordAutoReplyInfo);
				}
				autoReplyInfoRule.setKeyword_autoreply_info(listKeyWordAutoReplyInfo);
			}
		}
		
		return autoReplyInfoRule;
	}
}
