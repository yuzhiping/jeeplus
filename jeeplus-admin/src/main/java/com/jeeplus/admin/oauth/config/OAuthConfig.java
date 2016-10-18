package com.jeeplus.admin.oauth.config;

import com.jeeplus.admin.oauth.api.GithubAPI;
import com.jeeplus.admin.oauth.service.GithubOAuthService;
import com.jeeplus.admin.oauth.service.OAuthServiceDeractor;
import org.scribe.builder.ServiceBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author:yuzp17311
 * @version:v1.0
 * @date: 2016-10-28 18:24.
 */
@Configuration
public class OAuthConfig {

    private static final String CALLBACK_URL = "%s/oauth/%s/callback";

    @Value("${oAuth.github.state}")
    String state;
    @Value("${oAuth.github.clientId}")
    String githubClientId;
    @Value("${oAuth.github.clientSecret}")
    String githubClientSecret;
    @Value("${demo.host}") String host;

    @Bean
    public GithubAPI githubApi(){
        return new GithubAPI(state);
    }

    @Bean
    public OAuthServiceDeractor getGithubOAuthService(){
        return new GithubOAuthService(new ServiceBuilder()
                .provider(githubApi())
                .apiKey(githubClientId)
                .apiSecret(githubClientSecret)
                .callback(String.format(CALLBACK_URL, host, OAuthTypes.GITHUB))
                .build());
    }

}
