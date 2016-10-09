package com.jeeplus.admin.mapper;

import com.jeeplus.admin.dto.SysUserDTO;
import com.jeeplus.admin.entities.TbSysUser;
import org.apache.ibatis.annotations.Mapper;

/**
 * Created by yuzp17311 on 2016/8/24.
 */
@Mapper
public interface LoginMapper {

     TbSysUser loginValidate(SysUserDTO sysUserDTO);

}
