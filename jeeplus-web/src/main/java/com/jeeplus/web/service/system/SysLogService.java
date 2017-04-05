package com.jeeplus.web.service.system;

import com.jeeplus.web.entities.system.SysLogEntity;

import java.util.List;
import java.util.Map;

/**
 * 系统日志
 * @author:yuzp17311
 * @version:v1.0
 * @date: 2017-03-30 11:22.
 */
public interface SysLogService {

    SysLogEntity queryObject(Long id);

    List<SysLogEntity> queryList(Map<String, Object> map);

    int queryTotal(Map<String, Object> map);

    void save(SysLogEntity sysLog);

    void update(SysLogEntity sysLog);

    void delete(Long id);

    void deleteBatch(Long[] ids);

}
