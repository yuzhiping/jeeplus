package com.jeeplus.admin.services;

import com.jeeplus.admin.dto.SysUserDTO;
import com.jeeplus.admin.entities.TbSysUser;

/**
 * @author:yuzp17311
 * @version:v1.0
 * @date: 2016-10-31 13:52.
 */
public interface UserService {
    SysUserDTO findByUsername(String username);
    void save(TbSysUser user);
}
