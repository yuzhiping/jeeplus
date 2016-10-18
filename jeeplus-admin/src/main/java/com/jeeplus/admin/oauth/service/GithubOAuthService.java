package com.jeeplus.admin.oauth.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONPath;
import com.jeeplus.admin.oauth.config.OAuthTypes;
import com.jeeplus.admin.oauth.entities.OAuthUser;
import com.jeeplus.admin.oauth.entities.User;
import org.scribe.model.OAuthRequest;
import org.scribe.model.Response;
import org.scribe.model.Token;
import org.scribe.model.Verb;
import org.scribe.oauth.OAuthService;

/**
 * @author:yuzp17311
 * @version:v1.0
 * @date: 2016-10-28 18:19.
 */
public class GithubOAuthService extends OAuthServiceDeractor {

    private static final String PROTECTED_RESOURCE_URL = "https://api.github.com/user";

    public GithubOAuthService(OAuthService oAuthService) {
        super(oAuthService, OAuthTypes.GITHUB);
    }

    @Override
    public OAuthUser getOAuthUser(Token accessToken) {
        OAuthRequest request = new OAuthRequest(Verb.GET, PROTECTED_RESOURCE_URL);
        this.signRequest(accessToken, request);
        Response response = request.send();
        OAuthUser oAuthUser = new OAuthUser();
        oAuthUser.setoAuthType(getoAuthType());
        Object result = JSON.parse(response.getBody());
        oAuthUser.setoAuthId(JSONPath.eval(result, "$.id").toString());
        oAuthUser.setUser(new User());
        oAuthUser.getUser().setUsername(JSONPath.eval(result, "$.login").toString());
        return oAuthUser;
    }
}
