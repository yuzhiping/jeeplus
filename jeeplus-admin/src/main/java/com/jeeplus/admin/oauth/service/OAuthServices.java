package com.jeeplus.admin.oauth.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @author:yuzp17311
 * @version:v1.0
 * @date: 2016-10-28 18:27.
 */
@Service
public class OAuthServices {
    @Autowired
    List<OAuthServiceDeractor> oAuthServiceDeractors;

    public OAuthServiceDeractor getOAuthService(String type){
        Optional<OAuthServiceDeractor> oAuthService = oAuthServiceDeractors.stream().filter(o -> o.getoAuthType().equals(type))
                .findFirst();
        if(oAuthService.isPresent()){
            return oAuthService.get();
        }
        return null;
    }

    public List<OAuthServiceDeractor> getAllOAuthServices(){
        return oAuthServiceDeractors;
    }
}
