package com.jeeplus.blog.util.sina;

import weibo4j.Oauth;
import weibo4j.http.AccessToken;

/**
 * @author:yuzp17311
 * @version:v1.0
 * @date: 2017-05-24 19:40.
 */
public class SinaWeiBoUtils {

    public static AccessToken token(String code) throws Exception{
        Oauth oauth = new Oauth();
        oauth.authorize("code");
        return oauth.getAccessTokenByCode(code);
    }

}
