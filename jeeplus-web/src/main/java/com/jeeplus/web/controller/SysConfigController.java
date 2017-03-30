package com.jeeplus.web.controller;

import com.jeeplus.web.entities.system.SysConfigEntity;
import com.jeeplus.web.service.system.SysConfigService;
import com.jeeplus.web.util.PageUtils;
import com.jeeplus.web.commons.R;
import com.jeeplus.web.commons.exception.RRException;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 系统配置信息
 * @author:yuzp17311
 * @version:v1.0
 * @date: 2016-12-12 14:40.
 */
@RestController
@RequestMapping("/sys/config")
public class SysConfigController extends AbstractController {

    @Autowired
    private SysConfigService sysConfigService;

    /**

     * 所有配置列表

     */
    @RequestMapping("/list")
    @RequiresPermissions("sys:config:list")
    public R list(String key,Integer page, Integer limit){
        Map<String, Object> map = new HashMap<>();
        map.put("key", key);
        map.put("offset", (page - 1) * limit);
        map.put("limit", limit);

        //查询列表数据


        List<SysConfigEntity> configList = sysConfigService.queryList(map);
        int total = sysConfigService.queryTotal(map);

        PageUtils pageUtil = new PageUtils(configList, total, limit, page);

        return R.ok().put("page", pageUtil);
    }


    /**

     * 配置信息

     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("sys:config:info")
    public R info(@PathVariable("id") Long id){
        SysConfigEntity config = sysConfigService.queryObject(id);

        return R.ok().put("config", config);
    }

    /**

     * 保存配置

     */
    @RequestMapping("/save")
    @RequiresPermissions("sys:config:save")
    public R save(@RequestBody SysConfigEntity config){
        //参数校验


        verifyForm(config);

        sysConfigService.save(config);

        return R.ok();
    }

    /**

     * 修改配置

     */
    @RequestMapping("/update")
    @RequiresPermissions("sys:config:update")
    public R update(@RequestBody SysConfigEntity config){
        //参数校验


        verifyForm(config);

        sysConfigService.update(config);

        return R.ok();
    }

    /**

     * 删除配置

     */
    @RequestMapping("/delete")
    @RequiresPermissions("sys:config:delete")
    public R delete(@RequestBody Long[] ids){
        sysConfigService.deleteBatch(ids);

        return R.ok();
    }

    /**

     * 验证参数是否正确

     */
    private void verifyForm(SysConfigEntity config){
        if(StringUtils.isBlank(config.getKey())){
            throw new RRException("参数名不能为空");
        }

        if(StringUtils.isBlank(config.getValue())){
            throw new RRException("参数值不能为空");
        }
    }

}
