package com.jeeplus.web.service.system;

import com.jeeplus.web.entities.system.ScheduleJobLogEntity;

import java.util.List;
import java.util.Map;

/**
 * 定时任务日志
 * @author:yuzp17311
 * @version:v1.0
 * @date: 2016-12-11 16:55.
 */
public interface ScheduleJobLogService {

    /**

     * 根据ID，查询定时任务日志

     */
    ScheduleJobLogEntity queryObject(Long jobId);

    /**

     * 查询定时任务日志列表

     */
    List<ScheduleJobLogEntity> queryList(Map<String, Object> map);

    /**

     * 查询总数

     */
    int queryTotal(Map<String, Object> map);

    /**

     * 保存定时任务日志

     */
    void save(ScheduleJobLogEntity log);
}
