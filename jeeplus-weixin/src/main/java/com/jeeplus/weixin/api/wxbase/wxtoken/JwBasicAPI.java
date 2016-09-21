package com.jeeplus.weixin.api.wxbase.wxtoken;

import com.jeeplus.weixin.api.core.exception.WexinReqException;
import com.jeeplus.weixin.api.core.req.WeiXinReqService;
import com.jeeplus.weixin.api.core.req.model.AccessToken;
import com.jeeplus.weixin.api.core.req.model.ServiceIP;
import com.jeeplus.weixin.api.core.util.WeiXinConstant;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;


/**
 * 基础API
 * 对应微信API的 "基础支持"
 * http://mp.weixin.qq.com/wiki/14/9f9c82c1af308e3b14ba9b973f99a8ba.html
 * Created by yuzp17311 on 2016/8/23.
 * 
 */
public class JwBasicAPI {

    private static Logger logger= LoggerFactory.getLogger(JwBasicAPI.class);

    /**
     * 返回的信息名称
     */
    public static String RETURN_INFO_NAME = "ip_list";

	private static AccessToken accessToken = null;

	/**
	 * 获取权限令牌信息
	 * @param appid 第三方用户唯一凭证
	 * @param appscret 第三方用户唯一凭证密钥
	 * @throws WexinReqException
	 */
	public static String getAccessToken(String appid, String appscret) throws WexinReqException {
		String newAccessToken = "";
        accessToken = new AccessToken();
        accessToken.setAppid(appid);
        accessToken.setSecret(appscret);
		JSONObject result = WeiXinReqService.getInstance().doWeinxinReqJson(accessToken);
		// 正常返回
		newAccessToken = result.getString("access_token");
		return newAccessToken;
	}


    /**
     * 获取服务的ip列表信息
     * @param access_token 公众号的access_token
     * @return
     * @throws WexinReqException
     */
    public static List<String> getServiceIpList(String access_token) throws WexinReqException {
        List<String> lstServiceIp = null;
        if(access_token!=null){
            ServiceIP param = new ServiceIP();
            param.setAccess_token(access_token);
            JSONObject result = WeiXinReqService.getInstance().doWeinxinReqJson(param);
            Object error = result.get(WeiXinConstant.RETURN_ERROR_INFO_CODE);

            JSONArray infoArray = result.getJSONArray(RETURN_INFO_NAME);
            lstServiceIp = new ArrayList<String>(infoArray.size());
            for (int i = 0; i < infoArray.size(); i++) {
                lstServiceIp.add(infoArray.getString(i));
            }
        }else{
            logger.error("access_token为空");
            throw new WexinReqException("access_token为空");
        }
        return lstServiceIp;
    }

}
