package com.jeeplus.web.service.system.impl;

import com.jeeplus.web.entities.system.SysOssEntity;
import com.jeeplus.web.mapper.system.SysOssMapper;
import com.jeeplus.web.service.system.SysOssService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @author:yuzp17311
 * @version:v1.0
 * @date: 2017-03-30 16:01.
 */
@Service
public class SysOssServiceImpl implements SysOssService {

    @Autowired
    private SysOssMapper sysOssMapper;

    @Override
    public SysOssEntity queryObject(Long id) {
        return sysOssMapper.queryObject(id);
    }

    @Override
    public List<SysOssEntity> queryList(Map<String, Object> map) {
        return sysOssMapper.queryList(map);
    }

    @Override
    public int queryTotal(Map<String, Object> map) {
        return sysOssMapper.queryTotal(map);
    }

    @Override
    public void save(SysOssEntity sysOss) {
        sysOssMapper.save(sysOss);
    }

    @Override
    public void update(SysOssEntity sysOss) {
        sysOssMapper.update(sysOss);
    }

    @Override
    public void delete(Long id) {
        sysOssMapper.delete(id);
    }

    @Override
    public void deleteBatch(Long[] ids) {
        sysOssMapper.deleteBatch(ids);
    }
}
