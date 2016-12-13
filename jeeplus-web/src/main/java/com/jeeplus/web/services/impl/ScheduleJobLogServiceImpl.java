package com.jeeplus.web.services.impl;

import com.jeeplus.web.entities.ScheduleJobLogEntity;
import com.jeeplus.web.mapper.ScheduleJobLogMapper;
import com.jeeplus.web.services.ScheduleJobLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @author:yuzp17311
 * @version:v1.0
 * @date: 2016-12-11 17:03.
 */
@Service("scheduleJobLogService")
public class ScheduleJobLogServiceImpl implements ScheduleJobLogService {

    @Autowired
    private ScheduleJobLogMapper scheduleJobLogMapper;

    /**
     * 根据ID，查询定时任务日志
     *
     * @param jobId
     */
    @Override
    public ScheduleJobLogEntity queryObject(Long jobId) {
        return scheduleJobLogMapper.queryObject(jobId);
    }

    /**
     * 查询定时任务日志列表
     *
     * @param map
     */
    @Override
    public List<ScheduleJobLogEntity> queryList(Map<String, Object> map) {
        return scheduleJobLogMapper.queryList(map);
    }

    /**
     * 查询总数
     *
     * @param map
     */
    @Override
    public int queryTotal(Map<String, Object> map) {
        return scheduleJobLogMapper.queryTotal(map);
    }

    /**
     * 保存定时任务日志
     *
     * @param log
     */
    @Override
    public void save(ScheduleJobLogEntity log) {
        scheduleJobLogMapper.save(log);
    }
}
