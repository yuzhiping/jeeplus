package com.jeeplus.admin.oauth.service;

import com.jeeplus.admin.oauth.entities.OAuthUser;
import org.scribe.model.OAuthRequest;
import org.scribe.model.Token;
import org.scribe.model.Verifier;
import org.scribe.oauth.OAuthService;

/**
 * @author:yuzp17311
 * @version:v1.0
 * @date: 2016-10-28 18:15.
 */
public abstract class OAuthServiceDeractor implements OAuthService {

    private final OAuthService oAuthService;
    private final String oAuthType;
    private final String authorizationUrl;

    public OAuthServiceDeractor(OAuthService oAuthService, String type) {
        super();
        this.oAuthService = oAuthService;
        this.oAuthType = type;
        this.authorizationUrl = oAuthService.getAuthorizationUrl(null);
    }

    @Override
    public Token getRequestToken() {
        return oAuthService.getRequestToken();
    }

    @Override
    public Token getAccessToken(Token requestToken, Verifier verifier) {
        return oAuthService.getAccessToken(requestToken, verifier);
    }

    @Override
    public void signRequest(Token accessToken, OAuthRequest request) {
        oAuthService.signRequest(accessToken, request);
    }

    @Override
    public String getVersion() {
        return oAuthService.getVersion();
    }

    @Override
    public String getAuthorizationUrl(Token requestToken) {
        return oAuthService.getAuthorizationUrl(requestToken);
    }

    public String getoAuthType() {
        return oAuthType;
    }

    public String getAuthorizationUrl(){
        return authorizationUrl;
    }

    public abstract OAuthUser getOAuthUser(Token accessToken);

}
