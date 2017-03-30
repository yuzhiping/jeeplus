package com.jeeplus.web.service.system.impl;

import com.jeeplus.web.entities.system.SysLogEntity;
import com.jeeplus.web.mapper.system.SysLogMapper;
import com.jeeplus.web.service.system.SysLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @author:yuzp17311
 * @version:v1.0
 * @date: 2017-03-30 11:23.
 */
@Service
public class SysLogServiceImpl implements SysLogService {

    @Autowired
    private SysLogMapper sysLogMapper;

    @Override
    public SysLogEntity queryObject(Long id) {
        return sysLogMapper.queryObject(id);
    }

    @Override
    public List<SysLogEntity> queryList(Map<String, Object> map) {
        return sysLogMapper.queryList(map);
    }

    @Override
    public int queryTotal(Map<String, Object> map) {
        return sysLogMapper.queryTotal(map);
    }

    @Override
    public void save(SysLogEntity sysLog) {
        sysLogMapper.save(sysLog);
    }

    @Override
    public void update(SysLogEntity sysLog) {
        sysLogMapper.update(sysLog);
    }

    @Override
    public void delete(Long id) {
        sysLogMapper.delete(id);
    }

    @Override
    public void deleteBatch(Long[] ids) {
        sysLogMapper.deleteBatch(ids);
    }
}
