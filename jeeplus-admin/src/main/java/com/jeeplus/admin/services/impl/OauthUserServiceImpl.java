package com.jeeplus.admin.services.impl;

import com.jeeplus.admin.oauth.entities.OAuthUser;
import com.jeeplus.admin.services.IOauthUserService;
import org.springframework.stereotype.Service;

/**
 * @author:yuzp17311
 * @version:v1.0
 * @date: 2016-10-31 13:57.
 */
@Service
public class OauthUserServiceImpl implements IOauthUserService {
    public OAuthUser findByOAuthTypeAndOAuthId(String oAuthType, String oAuthId){
        return null;
    }


    public OAuthUser save(OAuthUser oAuthUser){
        return null;
    }

}
