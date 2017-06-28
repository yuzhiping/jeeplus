package com.jeeplus.weixin.service.weixin.impl;

import com.jeeplus.weixin.entities.UserInfoModel;
import com.jeeplus.weixin.fastweixin.api.config.ApiConfig;
import com.jeeplus.weixin.service.weixin.FwhloginService;
import com.jeeplus.weixin.service.weixin.FwhuserService;
import com.jeeplus.weixin.utils.Constants;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.net.URLEncoder;
import java.util.Enumeration;

/**
 * Created by 余智平 on 2017/4/10.
 */
@Service
public class FwhloginServiceImpl implements FwhloginService {

    @Autowired
    private FwhuserService fwhuserService;

    @Autowired
    private ApiConfig config;


    @Override
    public void getWxLoginuser(HttpServletRequest request, HttpServletResponse response) {

        long start = System.currentTimeMillis();
        try {

            HttpSession session = request.getSession();

            String basePath = request.getScheme()+"://"+request.getServerName()+request.getContextPath()+"/";

            // 是否需要获取jssdk标志
            boolean needJssdk = false;

            // 本次访问的URL ： basePath + uri + tempUrl(tempUrl去掉code参数)
            String uri = request.getRequestURI();
            uri = uri.replace(request.getContextPath() + "/", "");
            String tempUrl = "?a=b";
            @SuppressWarnings("unchecked")
            Enumeration<String> e = request.getParameterNames();
            String o = "";
            while (e.hasMoreElements()) {
                o = e.nextElement();
                if (!o.equals("code")) {
                    // 由于做Oauth验证的时候code作为票据进行验证，因此所有的URL中的code值不作为跳转的参数
                    tempUrl += "&" + o + "=" + request.getParameter(o.toString());
                }
            }

            // openid
            String openid = "";
            // 获取session中的值
            UserInfoModel userInfoModel = (UserInfoModel)session.getAttribute(Constants.USER_SESSION_LOGIN);

            if (Constants.DEBUG) {
                System.out.println("debug mode");
                // 调试模式下，直接通过获取Openid来设置值
                openid = request.getParameter("wecha_id");
                if (!StringUtils.isEmpty(openid)) {
                    // 获取member
                    userInfoModel = fwhuserService.checkOpenId(userInfoModel,openid);

                    session.setAttribute(Constants.USER_SESSION_LOGIN,userInfoModel);
                    needJssdk = true;
                    return;


                }
            }



            // code是OAuth票据
            String url ;
            String code = request.getParameter("code");
            if (StringUtils.isEmpty(code)) {
                System.out.println("code is null");
                // code为空的情况，要向微信获取票据
                url = Configure.OAUTH_GET_CODE;
                String reUrl = basePath + uri + tempUrl;
                reUrl = URLEncoder.encode(reUrl, "utf-8");
                url = String.format(url, Constants.APPID, reUrl, "snsapi_base");
                response.sendRedirect(url);
                return;
            } else {
                System.out.println("code not null");
                url = Configure.OAUTH_GET_OPENID;
                url = String.format(url, Constants.APPID, Constants.SECRET, code);
                JSONObject json = WeixinUtil.weixinPostJson(url, null, false);
                if (com.jeeplus.weixin.fastweixin.util.StringUtils.isEmpty(json)) {
                    System.out.println("StringUtil.isEmpty(json)");
                    return;
                } else {
                    if (!json.containsKey("openid")) {
                        System.out.println("!json.containsKey(openid)");
                        return;
                    }
                }
                openid = json.getString("openid");
                System.out.println("get openid:" + openid);
                // 获取member
                userInfoModel = fwhuserService.checkOpenId(userInfoModel,openid);

                if (!StringUtil.isEmpty(userInfoModel)) {
                    System.out.println("member not null");
                    // model不为空的情况，更新session

                    session.setAttribute(Constants.USER_SESSION_LOGIN,userInfoModel);
                    needJssdk = true;

                } else {
                    System.out.println("member is null");
                    return;
                }
            }

            // 获取jssdk
            if (needJssdk) {
                StringBuffer jsUrl = new StringBuffer();
                String queryString = request.getQueryString();
                String shareUrl = request.getRequestURL().toString();
                jsUrl.append(shareUrl);
                if (!StringUtils.isEmpty(queryString)) {
                    queryString = queryString.split("#")[0];
                    jsUrl.append("?");
                    jsUrl.append(queryString);
                }
                TicketModel ticket = fwhjssdkService.getTicket(jsUrl.toString());

                session.setAttribute("appid", ticket.getAppid());
                session.setAttribute("timestamp", ticket.getTimestamp());
                session.setAttribute("nonceStr", ticket.getNonceStr());
                session.setAttribute("signature", ticket.getSignature());
            }


            long end = System.currentTimeMillis();
            System.out.println("lanjieqi huafei le :" + (end - start) + "hao second");
            return;

        } catch(Exception e) {
            e.printStackTrace();
            return;
        }
    }

    }
}
