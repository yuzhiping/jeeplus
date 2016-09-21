package com.jeeplus.weixin.api.wxbase.wxmedia;

import java.io.File;
import java.util.Date;
import java.util.List;

import net.sf.json.JSONObject;

import org.apache.commons.lang3.StringUtils;
import com.jeeplus.weixin.api.core.common.WeixinHttpUtils;
import com.jeeplus.weixin.api.core.exception.WexinReqException;
import com.jeeplus.weixin.api.core.req.WeiXinReqService;
import com.jeeplus.weixin.api.core.req.model.DownloadMedia;
import com.jeeplus.weixin.api.core.req.model.UploadMedia;
import com.jeeplus.weixin.api.core.util.WeiXinConstant;
import com.jeeplus.weixin.api.core.util.WeiXinReqUtil;
import com.jeeplus.weixin.api.wxbase.wxmedia.model.WxArticlesRequest;
import com.jeeplus.weixin.api.wxbase.wxmedia.model.WxArticlesRequestByMaterial;
import com.jeeplus.weixin.api.wxbase.wxmedia.model.WxArticlesRespponseByMaterial;
import com.jeeplus.weixin.api.wxbase.wxmedia.model.WxCountResponse;
import com.jeeplus.weixin.api.wxbase.wxmedia.model.WxDescriptionRequest;
import com.jeeplus.weixin.api.wxbase.wxmedia.model.WxDwonload;
import com.jeeplus.weixin.api.wxbase.wxmedia.model.WxMediaForMaterial;
import com.jeeplus.weixin.api.wxbase.wxmedia.model.WxMediaForMaterialResponse;
import com.jeeplus.weixin.api.wxbase.wxmedia.model.WxNews;
import com.jeeplus.weixin.api.wxbase.wxmedia.model.WxUpdateArticle;
import com.jeeplus.weixin.api.wxbase.wxmedia.model.WxUpload;
import com.jeeplus.weixin.api.wxsendmsg.JwSendMessageAPI;
import com.jeeplus.weixin.api.wxsendmsg.model.WxArticle;
import com.jeeplus.weixin.api.wxsendmsg.model.WxArticlesResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 对应微信API的 "素材管理"
 * http://mp.weixin.qq.com/wiki/9/677a85e3f3849af35de54bb5516c2521.html
 * Created by yuzp17311 on 2016/8/23.
 */
public class JwMediaAPI {
	private static Logger logger = LoggerFactory.getLogger(JwMediaAPI.class);
	// 新增永久图文素材
	private static String material_add_news_url ="https://api.weixin.qq.com/cgi-bin/material/add_news?access_token=ACCESS_TOKEN";
	// 新增其他类型永久素材
	private static String material_add_material_url = "https://api.weixin.qq.com/cgi-bin/material/add_material?access_token=ACCESS_TOKEN";
	// 获取永久素材
	private static String material_get_material_url = "https://api.weixin.qq.com/cgi-bin/material/get_material?access_token=ACCESS_TOKEN";
	// 获取素材总数
	private static String material_get_materialcount_url = "https://api.weixin.qq.com/cgi-bin/material/get_materialcount?access_token=ACCESS_TOKEN";
	// 修改永久图文素材
	private static String material_update_news_url = "https://api.weixin.qq.com/cgi-bin/material/update_news?access_token=ACCESS_TOKEN";
	// 获取素材列表
	private static String material_batchget_material_url = "https://api.weixin.qq.com/cgi-bin/material/batchget_material?access_token=ACCESS_TOKEN";
	
	/**
	 * 
	 * @param access_token
	 * @param type  媒体文件类型
     *              分别有图片（image），1MB，支持JPG格式
     *              语音（voice），1MB，播放长度不超过60s，支持MP4格式
     *              视频（video），10MB，支持MP4格式
     *              缩略图（thumb），64KB，支持JPG格式
	 * @param fileNamePath  上传的文件目录
	 * @return WxUpload
	 * @throws WexinReqException
	 */
	public static WxUpload uploadMedia(String access_token,String type,String fileNamePath) throws WexinReqException{
		UploadMedia uploadMedia = new UploadMedia();
		uploadMedia.setAccess_token(access_token);
		uploadMedia.setFilePathName(fileNamePath);
		uploadMedia.setType(type);
		JSONObject result = WeiXinReqService.getInstance().doWeinxinReqJson(uploadMedia);
		Object error = result.get(WeiXinConstant.RETURN_ERROR_INFO_CODE);
        WxUpload wxMedia = (WxUpload) JSONObject.toBean(result, WxUpload.class);
		return wxMedia;
	}
	
	
	/**
	 * 下载多媒体文件
	 * @param access_token 调用接口凭证
	 * @param media_id  媒体文件ID
	 * @param filePath 媒体文件保存路径
	 * @return
	 * @throws WexinReqException
	 */
	public static WxDwonload downloadMedia(String access_token,String media_id,String filePath) throws WexinReqException{
		DownloadMedia downloadMedia = new DownloadMedia();
		downloadMedia.setAccess_token(access_token);
		downloadMedia.setFilePath(filePath);
		downloadMedia.setMedia_id(media_id);
		JSONObject result = WeiXinReqService.getInstance().doWeinxinReqJson(downloadMedia);
		Object error = result.get(WeiXinConstant.RETURN_ERROR_INFO_CODE);
        WxDwonload wxMedia = (WxDwonload) JSONObject.toBean(result, WxDwonload.class);
		return wxMedia;
	}
	

	/**
	 * 新增永久图文素材
     * http://mp.weixin.qq.com/wiki/10/10ea5a44870f53d79449290dfd43d006.html
     * @param access_token 调用接口凭证
	 * @param wxArticles
	 *            图文集合，若新增的是多图文素材,最多8段
	 * @return WxArticlesResponse 上传图文消息素材返回结果
	 * @throws WexinReqException
	 */
	public static WxArticlesResponse uploadArticlesByMaterial(String access_token, List<WxArticle> wxArticles) throws WexinReqException {
		WxArticlesResponse wxArticlesResponse = null;
		if (wxArticles.size() == 0) {
			logger.error("没有上传的图文消息");
		} else if (wxArticles.size() > 8) {
			logger.error("图文消息最多为8个图文");
		} else {
			if (access_token != null) {
				String requestUrl = material_add_news_url.replace("ACCESS_TOKEN", access_token);

				for (WxArticle article : wxArticles) {
					if (article.getFileName() != null && article.getFileName().length() > 0) {
						try {
							String mediaId = JwSendMessageAPI.getFileMediaId(access_token, article);
							article.setThumb_media_id(mediaId);

						} catch (Exception e) {
							throw new WexinReqException(e);
						}
					}
				}
				WxArticlesRequest wxArticlesRequest = new WxArticlesRequest();
				wxArticlesRequest.setArticles(wxArticles);
				JSONObject obj = JSONObject.fromObject(wxArticlesRequest);
				JSONObject result = WeixinHttpUtils.httpRequest(requestUrl, "POST", obj.toString());
				if (result.has("errcode")) {
					logger.error("新增永久图文素材失败！errcode=" + result.getString("errcode") + ",errmsg = " + result.getString("errmsg"));
                    throw new WexinReqException("新增永久图文素材失败！errcode=" + result.getString("errcode") + ",errmsg = " + result.getString("errmsg"));
				} else {
					wxArticlesResponse = new WxArticlesResponse();
					wxArticlesResponse.setMedia_id(result.getString("media_id"));
					wxArticlesResponse.setType(result.getString("type"));
					wxArticlesResponse.setCreated_at(new Date(result.getLong("created_at") * 1000));
				}

			}
		}

		return wxArticlesResponse;
	}
	
	
	/**
	 * 获取素材总数
	 * http://mp.weixin.qq.com/wiki/5/a641fd7b5db7a6a946ebebe2ac166885.html
	 * @param access_token 调用接口凭证
	 * @return WxCountResponse 素材数目返回结果
	 * @throws WexinReqException
	 */
	public static WxCountResponse getMediaCount(String access_token) throws WexinReqException {
		WxCountResponse wxCountResponse = null;
		if (access_token != null) {
			String requestUrl = material_get_materialcount_url.replace("ACCESS_TOKEN", access_token);

			JSONObject result = WeixinHttpUtils.httpRequest(requestUrl, "POST",null);
			if (result.has("errcode")) {
				logger.error("获取素材总数失败！errcode=" + result.getString("errcode") + ",errmsg = " + result.getString("errmsg"));
                throw new WexinReqException("获取素材总数失败！errcode=" + result.getString("errcode") + ",errmsg = " + result.getString("errmsg"));
			} else {
				wxCountResponse = new WxCountResponse();
				wxCountResponse.setImage_count(result.getString("image_count"));
				wxCountResponse.setNews_count(result.getString("news_count"));
				wxCountResponse.setVideo_count(result.getString("video_count"));
				wxCountResponse.setVoice_count(result.getString("voice_count"));
			}
		}
		return wxCountResponse;
	}
	
	/**
	 * 获取永久素材
	 * http://mp.weixin.qq.com/wiki/12/3c12fac7c14cb4d0e0d4fe2fbc87b638.html
	 * @param access_token 调用接口凭证
	 * @param media_id 要获取的素材的media_id
	 * @return WxArticlesResponse 获取图文消息素材返回结果
	 * @throws WexinReqException
	 */
	public static WxArticlesRespponseByMaterial getArticlesByMaterial(String access_token,String media_id) throws WexinReqException {
		WxArticlesRespponseByMaterial wxArticlesRespponseByMaterial = null;
		
			if (access_token != null) {
				String requestUrl = material_get_material_url.replace("ACCESS_TOKEN", access_token);
				WxArticlesRequestByMaterial wxArticlesRequestByMaterial = new WxArticlesRequestByMaterial();
				wxArticlesRequestByMaterial.setMediaId(media_id);
				JSONObject obj = JSONObject.fromObject(wxArticlesRequestByMaterial);
				JSONObject result = WeixinHttpUtils.httpRequest(requestUrl, "POST", obj.toString());
				if (result.has("errcode")) {
					logger.error("获取永久素材失败！errcode=" + result.getString("errcode") + ",errmsg = " + result.getString("errmsg"));
                    throw new WexinReqException("获取永久素材失败！errcode=" + result.getString("errcode") + ",errmsg = " + result.getString("errmsg"));
				} else {
					wxArticlesRespponseByMaterial = (WxArticlesRespponseByMaterial)JSONObject.toBean(result,WxArticlesRespponseByMaterial.class);
				}
		}

		return wxArticlesRespponseByMaterial;
	}
	
	/**
	 * 删除永久素材
	 * http://mp.weixin.qq.com/wiki/7/2212203f4e17253b9aef77dc788f5337.html
	 * @param access_token 调用接口凭证
	 * @param media_id 要获取的素材的media_id
	 * @throws WexinReqException
	 */
	public static void deleteArticlesByMaterial(String access_token,String media_id) throws WexinReqException {
			if (access_token != null&&StringUtils.isNotEmpty(media_id)) {
				String requestUrl = material_get_material_url.replace("ACCESS_TOKEN", access_token);
				WxArticlesRequestByMaterial wxArticlesRequestByMaterial = new WxArticlesRequestByMaterial();
				wxArticlesRequestByMaterial.setMediaId(media_id);
				JSONObject obj = JSONObject.fromObject(wxArticlesRequestByMaterial);
				JSONObject result = WeixinHttpUtils.httpRequest(requestUrl, "POST", obj.toString());
				if (result.has("errcode")&&result.get("errcode")!="0") {
					logger.error("删除永久素材失败！errcode=" + result.getString("errcode") + ",errmsg = " + result.getString("errmsg"));
                    throw new WexinReqException("删除永久素材失败！errcode=" + result.getString("errcode") + ",errmsg = " + result.getString("errmsg"));
				}
		}
	}
	
	/**
	 * 修改永久图文素材
	 * http://mp.weixin.qq.com/wiki/10/c7bad9a463db20ff8ccefeedeef51f9e.html
	 * @param access_token 调用接口凭证
	 * @param wxUpdateArticle
	 * @throws WexinReqException
	 */
	public static void updateArticlesByMaterial(String access_token,WxUpdateArticle wxUpdateArticle) throws WexinReqException {
		if (access_token != null) {
			String requestUrl = material_update_news_url.replace("ACCESS_TOKEN", access_token);
			JSONObject obj = JSONObject.fromObject(wxUpdateArticle);
			JSONObject result = WeixinHttpUtils.httpRequest(requestUrl, "POST", obj.toString());
			if (result.has("errcode")&&result.get("errcode")!="0") {
				logger.error("消息失败！errcode=" + result.getString("errcode") + ",errmsg = " + result.getString("errmsg"));
                throw new WexinReqException("修改永久图文素材失败！errcode=" + result.getString("errcode") + ",errmsg = " + result.getString("errmsg"));
			}
		}
	}

    /**
     * 获取素材列表
     * http://mp.weixin.qq.com/wiki/15/8386c11b7bc4cdd1499c572bfe2e95b3.html
     * @param access_token 调用接口凭证
     * @param type 素材的类型，图片（image）、视频（video）、语音 （voice）、图文（news）
     * @param offset 从全部素材的该偏移位置开始返回，0表示从第一个素材 返回
     * @param count 返回素材的数量，取值在1到20之间
     * @return
     * @throws WexinReqException
     */
	public static WxNews queryArticlesByMaterial(String access_token,String type,int offset,int count) throws WexinReqException {
		WxNews news = null;
		if (access_token != null) {
			String requestUrl = material_batchget_material_url.replace("ACCESS_TOKEN", access_token);
			
			JSONObject obj = new JSONObject();
			obj.put("type", type);
			obj.put("offset", offset);
			obj.put("count", count);
			JSONObject result = WeixinHttpUtils.httpRequest(requestUrl, "POST", obj.toString());
			if (result.has("errcode")&&result.get("errcode")!="0") {
				logger.error("获取素材列表失败！errcode=" + result.getString("errcode") + ",errmsg = " + result.getString("errmsg"));
                throw new WexinReqException("获取素材列表失败！errcode=" + result.getString("errcode") + ",errmsg = " + result.getString("errmsg"));
			} else{
                news = (WxNews) JSONObject.toBean(result, WxNews.class);
			}
		}
		return news;
	}
	/**
	 * 新增永久图文素材
	 * @param access_token 调用接口凭证
	 * @param wxArticles
	 * @return 素材的media_id
	 * @throws WexinReqException
	 */
	public static String getMediaIdByMaterial(String access_token, List<WxArticle> wxArticles) throws WexinReqException {

		WxArticlesResponse response = uploadArticlesByMaterial(access_token, wxArticles);
		if (response == null) {
			throw new WexinReqException("获取图文的mediaId失败");
		}
		return response.getMedia_id();
	}
	/**
	 * 新增其他类型永久素材
	 * 
	 * @param access_token
	 * @param wx
	 *            媒体文件类型，分别有图片（image）、语音（voice）、视频（video）和缩略图（thumb）
	 * @return
	 * @throws Exception
	 */
	public static WxMediaForMaterialResponse uploadMediaFileByMaterial(String access_token, WxMediaForMaterial wx) throws WexinReqException {
		WxMediaForMaterialResponse mediaResource = null;
		if (access_token != null) {
			String requestUrl = material_add_material_url.replace("ACCESS_TOKEN", access_token);

			File file = new File(wx.getFilePath() + wx.getFileName());
			String contentType = WeiXinReqUtil.getFileContentType(wx.getFileName().substring(wx.getFileName().lastIndexOf(".") + 1));
			JSONObject result = WeixinHttpUtils.uploadMediaFile(requestUrl, file, contentType);
			if("video"==wx.getType()){
				WxDescriptionRequest wr = new WxDescriptionRequest();
				wr.setDescription(wx.getWxDescription());
				JSONObject obj = JSONObject.fromObject(wr);
				WeixinHttpUtils.httpRequest(requestUrl, "POST", obj.toString());
			}
			if (result.containsKey("errcode")) {
				logger.error("上传媒体资源失败！errcode=" + result.getString("errcode") + ",errmsg = " + result.getString("errmsg"));
                throw new WexinReqException("上传媒体资源失败！errcode=" + result.getString("errcode") + ",errmsg = " + result.getString("errmsg"));
			} else {
				mediaResource = new WxMediaForMaterialResponse();
				mediaResource.setMedia_id(result.getString("media_id"));
				mediaResource.setUrl(result.getString("url"));
			}
		}
		return mediaResource;
	}
	/**
	 * 永久获取多媒体资源的mediaId
	 * 
	 * @param access_token
	 * @param wxMediaForMaterial
	 * @return
	 * @throws WexinReqException
	 */
	public static String getMediaIdForMaterial(String access_token, WxMediaForMaterial wxMediaForMaterial) throws WexinReqException {

		WxMediaForMaterialResponse response = uploadMediaFileByMaterial(access_token, wxMediaForMaterial);
		if (response == null) {
			throw new WexinReqException("获取多媒体资源的mediaId失败");
		}
		return response.getMedia_id();

	}

}
