package com.jeeplus.weixin.api.core.handler.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Map;

import org.apache.log4j.Logger;
import com.jeeplus.weixin.api.core.annotation.ReqType;
import com.jeeplus.weixin.api.core.exception.WexinReqException;
import com.jeeplus.weixin.api.core.handler.WeiXinReqHandler;
import com.jeeplus.weixin.api.core.req.model.WeixinReqConfig;
import com.jeeplus.weixin.api.core.req.model.WeixinReqParam;
import com.jeeplus.weixin.api.core.util.HttpRequestProxy;
import com.jeeplus.weixin.api.core.util.WeiXinReqUtil;
import com.jeeplus.weixin.api.coupon.location.model.LocationInfo;

/**
 * 对应微信API的“微信卡卷”--“创建卡卷”--“上传卡卷LOGO”
 * http://mp.weixin.qq.com/wiki/15/e33671f4ef511b77755142b37502928f.html#.E6.AD.A5.E9.AA.A4.E4.B8.80.EF.BC.9A.E4.B8.8A.E4.BC.A0.E5.8D.A1.E5.88.B8LOGO
 * Created by yuzp17311 on 2016/8/22.
 */
public class WeixinReqLogoUploadHandler implements WeiXinReqHandler {

	private static Logger logger = Logger.getLogger(WeixinReqLogoUploadHandler.class);

    /**
     * 处理请求
     * @param weixinReqParam
     * @return
     * @throws WexinReqException
     */
	@SuppressWarnings("rawtypes")
	public String doRequest(WeixinReqParam weixinReqParam) throws WexinReqException {
		String strReturnInfo = "";
		if(weixinReqParam instanceof LocationInfo){
			LocationInfo uploadMedia = (LocationInfo) weixinReqParam;
			ReqType reqType = uploadMedia.getClass().getAnnotation(ReqType.class);
			WeixinReqConfig objConfig = WeiXinReqUtil.getWeixinReqConfig(reqType.value());
			if(objConfig != null){
				String reqUrl = objConfig.getUrl();
				String fileName = uploadMedia.getFilePathName();
				File file = new File(fileName) ;
				InputStream fileIn = null;
				try {
					fileIn = new FileInputStream(file);
					String extName = fileName.substring(fileName.lastIndexOf(".") + 1);//扩展名
					String contentType = WeiXinReqUtil.getFileContentType(extName);
					if(contentType == null){
						logger.error("没有找到对应的文件类型");
					}
					Map parameters = WeiXinReqUtil.getWeixinReqParam(weixinReqParam);
					parameters.remove("filePathName");
					strReturnInfo = HttpRequestProxy.uploadMedia(reqUrl, parameters, "UTF-8", fileIn, file.getName(), contentType);
				} catch (FileNotFoundException e) {
				    logger.trace(e);
					throw new WexinReqException(e);
				}
			}
		}else if(weixinReqParam instanceof LocationInfo){
			LocationInfo uploadMedia = (LocationInfo) weixinReqParam;
			ReqType reqType = uploadMedia.getClass().getAnnotation(ReqType.class);
			WeixinReqConfig objConfig = WeiXinReqUtil.getWeixinReqConfig(reqType.value());
			if(objConfig != null){
				String reqUrl = objConfig.getUrl();
				String fileName = uploadMedia.getFilePathName();
				File file = new File(fileName) ;
				InputStream fileIn = null;
				try {
					fileIn = new FileInputStream(file);
					String extName = fileName.substring(fileName.lastIndexOf(".") + 1);//扩展名
					String contentType = WeiXinReqUtil.getFileContentType(extName);
					if(contentType == null || !contentType.equals("image/jpeg")){
						throw new WexinReqException("上传LOGO 大小限制1MB，像素为300*300，支持JPG格式以达到最佳效果");
					}
					Map parameters = WeiXinReqUtil.getWeixinReqParam(weixinReqParam);
					parameters.remove("filePathName");
					strReturnInfo = HttpRequestProxy.uploadMedia(reqUrl, parameters, "UTF-8", fileIn, file.getName(), contentType);
				} catch (FileNotFoundException e) {
					throw new WexinReqException(e);
				}
			}
		}else{
			logger.info("没有找到对应的配置信息");
		}
		return strReturnInfo;
	}

}
