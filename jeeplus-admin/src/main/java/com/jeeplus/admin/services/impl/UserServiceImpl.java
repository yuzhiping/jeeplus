package com.jeeplus.admin.services.impl;

import com.jeeplus.admin.oauth.entities.User;
import com.jeeplus.admin.services.IUserService;
import org.springframework.stereotype.Service;

/**
 * @author:yuzp17311
 * @version:v1.0
 * @date: 2016-10-31 13:56.
 */
@Service
public class UserServiceImpl implements IUserService {
    @Override
    public User findByUsername(String username) {
        return null;
    }

    public  User save(User user){
        return null;
    }

}
