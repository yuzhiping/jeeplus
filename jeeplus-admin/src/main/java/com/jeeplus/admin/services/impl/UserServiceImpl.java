package com.jeeplus.admin.services.impl;

import com.jeeplus.admin.dto.SysUserDTO;
import com.jeeplus.admin.entities.TbSysUser;
import com.jeeplus.admin.services.UserService;
import org.springframework.stereotype.Service;

/**
 * @author:yuzp17311
 * @version:v1.0
 * @date: 2016-10-31 13:56.
 */
@Service
public class UserServiceImpl implements UserService {
    @Override
    public SysUserDTO findByUsername(String username) {
        return null;
    }

    public  void save(TbSysUser user){

    }

}
