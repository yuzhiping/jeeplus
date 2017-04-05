package com.jeeplus.web.service.system;

import com.jeeplus.web.entities.system.SysOssEntity;

import java.util.List;
import java.util.Map;

/**
 * 文件上传
 * @author:yuzp17311
 * @version:v1.0
 * @date: 2017-03-30 15:58.
 */
public interface SysOssService {

    SysOssEntity queryObject(Long id);

    List<SysOssEntity> queryList(Map<String, Object> map);

    int queryTotal(Map<String, Object> map);

    void save(SysOssEntity sysOss);

    void update(SysOssEntity sysOss);

    void delete(Long id);

    void deleteBatch(Long[] ids);

}
