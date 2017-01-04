package com.jeeplus.web.services.impl;

import com.jeeplus.web.entities.ScheduleJobEntity;
import com.jeeplus.web.mapper.ScheduleJobMapper;
import com.jeeplus.web.services.ScheduleJobService;
import com.jeeplus.web.utils.Constant;
import com.jeeplus.web.utils.ScheduleUtils;
import org.quartz.CronTrigger;
import org.quartz.Scheduler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author:yuzp17311
 * @version:v1.0
 * @date: 2016-12-12 9:48.
 */
@Service
public class ScheduleJobServiceImpl implements ScheduleJobService {


    @Autowired
    private Scheduler scheduler;

    @Autowired
    private ScheduleJobMapper scheduleJobMapper;


    /**

     * 项目启动时，初始化定时器

     */
    @PostConstruct
    public void init(){
        List<ScheduleJobEntity> scheduleJobList = scheduleJobMapper.queryList(new HashMap<String, Object>());
        for(ScheduleJobEntity scheduleJob : scheduleJobList){
            CronTrigger cronTrigger = ScheduleUtils.getCronTrigger(scheduler, scheduleJob.getJobId());
            //如果不存在，则创建


            if(cronTrigger == null) {
                ScheduleUtils.createScheduleJob(scheduler, scheduleJob);
            }else {
                ScheduleUtils.updateScheduleJob(scheduler, scheduleJob);
            }
        }
    }


    /**
     * 根据ID，查询定时任务
     *
     * @param jobId
     */
    @Override
    public ScheduleJobEntity queryObject(Long jobId) {
        return scheduleJobMapper.queryObject(jobId);
    }

    /**
     * 查询定时任务列表
     *
     * @param map
     */
    @Override
    public List<ScheduleJobEntity> queryList(Map<String, Object> map) {
        return scheduleJobMapper.queryList(map);
    }

    /**
     * 查询总数
     *
     * @param map
     */
    @Override
    public int queryTotal(Map<String, Object> map) {
        return scheduleJobMapper.queryTotal(map);
    }

    /**
     * 保存定时任务
     *
     * @param scheduleJob
     */
    @Override
    @Transactional
    public void save(ScheduleJobEntity scheduleJob) {
        scheduleJob.setCreateTime(new Date());
        scheduleJob.setStatus(Constant.ScheduleStatus.NORMAL.getValue());
        scheduleJobMapper.save(scheduleJob);

        ScheduleUtils.createScheduleJob(scheduler, scheduleJob);
    }

    /**
     * 更新定时任务
     *
     * @param scheduleJob
     */
    @Override
    @Transactional
    public void update(ScheduleJobEntity scheduleJob) {
        ScheduleUtils.updateScheduleJob(scheduler, scheduleJob);

        scheduleJobMapper.update(scheduleJob);

    }

    /**
     * 批量删除定时任务
     *
     * @param jobIds
     */
    @Override
    @Transactional
    public void deleteBatch(Long[] jobIds) {
        for(Long jobId : jobIds){
            ScheduleUtils.deleteScheduleJob(scheduler, jobId);
        }

        //删除数据


        scheduleJobMapper.deleteBatch(jobIds);

    }

    /**
     * 批量更新定时任务状态
     *
     * @param jobIds
     * @param status
     */
    @Override
    @Transactional
    public int updateBatch(Long[] jobIds, int status) {
        Map<String, Object> map = new HashMap<>();
        map.put("list", jobIds);
        map.put("status", status);
        return scheduleJobMapper.updateBatch(map);
    }

    /**
     * 立即执行
     *
     * @param jobIds
     */
    @Override
    @Transactional
    public void run(Long[] jobIds) {
        for(Long jobId : jobIds){
            ScheduleUtils.run(scheduler, queryObject(jobId));
        }
    }

    /**
     * 暂停运行
     *
     * @param jobIds
     */
    @Override
    @Transactional
    public void pause(Long[] jobIds) {
        for(Long jobId : jobIds){
            ScheduleUtils.pauseJob(scheduler, jobId);
        }

        updateBatch(jobIds, Constant.ScheduleStatus.PAUSE.getValue());

    }

    /**
     * 恢复运行
     *
     * @param jobIds
     */
    @Override
    @Transactional
    public void resume(Long[] jobIds) {
        for(Long jobId : jobIds){
            ScheduleUtils.resumeJob(scheduler, jobId);
        }

        updateBatch(jobIds, Constant.ScheduleStatus.NORMAL.getValue());
    }
}
