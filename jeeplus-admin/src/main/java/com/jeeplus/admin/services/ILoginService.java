package com.jeeplus.admin.services;

import com.jeeplus.admin.dto.SysUserDTO;

/**
 * Created by yuzp17311 on 2016/8/24.
 */
public interface ILoginService {

    /**
     * 登录验证
     * @return
     */
    boolean loginValidate(SysUserDTO sysUserDTO);

}
