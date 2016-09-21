package com.jeeplus.weixin.api.core.req;

import com.jeeplus.weixin.api.core.annotation.ReqType;
import com.jeeplus.weixin.api.core.exception.WexinReqException;
import com.jeeplus.weixin.api.core.handler.WeiXinReqHandler;
import com.jeeplus.weixin.api.core.req.model.DownloadMedia;
import com.jeeplus.weixin.api.core.req.model.WeixinReqConfig;
import com.jeeplus.weixin.api.core.req.model.WeixinReqParam;
import com.jeeplus.weixin.api.core.util.WeiXinConstant;
import com.jeeplus.weixin.api.core.util.WeiXinReqUtil;
import net.sf.json.JSONObject;
import org.jdom.JDOMException;

import java.io.IOException;

/**
 * 获取微信接口的信息
 * Created by yuzp17311 on 2016/8/22.
 */
public class WeiXinReqService {
    private static WeiXinReqService weiXinReqService=null;

    private WeiXinReqService() {
        String realPath = WeiXinReqService.class.getClassLoader().getResource("").getFile();
        try {
            WeiXinReqUtil.initReqConfig("weixin-reqcongfig.xml");
        } catch (JDOMException e) {

            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * 获取公共的调用处理
     * @return
     */
    public static WeiXinReqService getInstance() {
        if (weiXinReqService == null) {
            // 同步块，线程安全的创建实例
            synchronized (WeiXinReqService.class) {
                // 再次检查实例是否存在，如果不存在才真正的创建实例
                if (weiXinReqService == null) {
                    weiXinReqService = new WeiXinReqService();
                }
            }
        }
        return weiXinReqService;
    }

    /**
     * 传入请求的参数，获取对应额接口返回信息
     * @param weixinReqParam
     * @return
     * @throws WexinReqException
     */
    public String doWeinxinReq(WeixinReqParam weixinReqParam)
            throws WexinReqException {
        String strReturnInfo = "";
        if (weixinReqParam.getClass().isAnnotationPresent(ReqType.class)) {
            ReqType reqType = weixinReqParam.getClass().getAnnotation(ReqType.class);
            WeixinReqConfig objConfig = WeiXinReqUtil.getWeixinReqConfig(reqType.value());
            WeiXinReqHandler handler = WeiXinReqUtil.getMappingHander(objConfig.getMappingHandler());
            strReturnInfo = handler.doRequest(weixinReqParam);
        }
        return strReturnInfo;
    }

    /**
     * 返回json对象
     * @param weixinReqParam
     * @return
     * @throws WexinReqException
     */
    public JSONObject doWeinxinReqJson(WeixinReqParam weixinReqParam) throws WexinReqException{
        String strResult = this.doWeinxinReq(weixinReqParam);
        JSONObject result = JSONObject.fromObject(strResult);
        Object error = result.get(WeiXinConstant.RETURN_ERROR_INFO_CODE);
        if(error !=null && Integer.parseInt(error.toString())!=0){
            throw new WexinReqException(result.toString());
        }
        return result;
    }

}
