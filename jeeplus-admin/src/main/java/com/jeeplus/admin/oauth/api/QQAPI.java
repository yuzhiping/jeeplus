package com.jeeplus.admin.oauth.api;

import org.scribe.builder.api.DefaultApi20;
import org.scribe.model.OAuthConfig;
import org.scribe.utils.OAuthEncoder;

/**
 * @author:yuzp17311
 * @version:v1.0
 * @date: 2016-10-28 18:03.
 */
public class QQAPI extends DefaultApi20 {

    private static final String AUTHORIZE_URL = "https://graph.qq.com/oauth2.0/authorize?response_type=code&client_id=%s&redirect_uri=%s&state=%s";
    private static final String SCOPED_AUTHORIZE_URL = AUTHORIZE_URL + "&scope=%s";
    private static final String ACCESS_TOKEN_URL = "https://graph.qq.com/oauth2.0/token?grant_type=authorization_code&state=%s";

    private final String qqState;

    public QQAPI(String state){
        this.qqState = state;
    }

    @Override
    public String getAccessTokenEndpoint() {
        return String.format(ACCESS_TOKEN_URL, qqState);
    }

    @Override
    public String getAuthorizationUrl(OAuthConfig oAuthConfig) {
        if (oAuthConfig.hasScope()){
            return String.format(SCOPED_AUTHORIZE_URL, oAuthConfig.getApiKey(), OAuthEncoder.encode(oAuthConfig.getCallback()),
                    qqState, OAuthEncoder.encode(oAuthConfig.getScope()));
        }
        else{
            return String.format(AUTHORIZE_URL, oAuthConfig.getApiKey(), OAuthEncoder.encode(oAuthConfig.getCallback()), qqState);
        }
    }
}
