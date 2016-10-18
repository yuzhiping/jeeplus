package com.jeeplus.admin.services;

import com.jeeplus.admin.oauth.entities.User;

/**
 * @author:yuzp17311
 * @version:v1.0
 * @date: 2016-10-31 13:52.
 */
public interface IUserService {
    User findByUsername(String username);
    User save(User user);
}
