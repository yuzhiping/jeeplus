package com.jeeplus.web.controller;

import com.jeeplus.web.common.Query;
import com.jeeplus.web.common.R;
import com.jeeplus.web.entities.system.SysLogEntity;
import com.jeeplus.web.service.system.SysLogService;
import com.jeeplus.web.util.PageUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

/**
 * 系统日志
 * @author:yuzp17311
 * @version:v1.0
 * @date: 2017-03-30 13:21.
 */
@Controller
@RequestMapping("/sys/log")
public class SysLogController {


    @Autowired
    private SysLogService sysLogService;

    /**
     * 列表
     */
    @ResponseBody
    @RequestMapping("/list")
    @RequiresPermissions("sys:log:list")
    public R list(@RequestParam Map<String, Object> params){
        //查询列表数据
        Query query = new Query(params);
        List<SysLogEntity> sysLogList = sysLogService.queryList(query);
        int total = sysLogService.queryTotal(query);

        PageUtils pageUtil = new PageUtils(sysLogList, total, query.getLimit(), query.getPage());

        return R.ok().put("page", pageUtil);
    }

}
