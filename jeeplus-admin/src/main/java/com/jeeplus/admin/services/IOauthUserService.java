package com.jeeplus.admin.services;

import com.jeeplus.admin.oauth.entities.OAuthUser;

/**
 * @author:yuzp17311
 * @version:v1.0
 * @date: 2016-10-31 13:54.
 */
public interface IOauthUserService {
    OAuthUser findByOAuthTypeAndOAuthId(String oAuthType, String oAuthId);

    OAuthUser save(OAuthUser oAuthUser);

}
