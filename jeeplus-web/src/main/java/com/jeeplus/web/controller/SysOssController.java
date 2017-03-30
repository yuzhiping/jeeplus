package com.jeeplus.web.controller;

import com.alibaba.fastjson.JSON;
import com.jeeplus.web.common.Query;
import com.jeeplus.web.common.R;
import com.jeeplus.web.common.constant.ConfigConstant;
import com.jeeplus.web.common.constant.Constant;
import com.jeeplus.web.common.exception.RRException;
import com.jeeplus.web.common.validator.group.AliyunGroup;
import com.jeeplus.web.common.validator.group.QcloudGroup;
import com.jeeplus.web.common.validator.group.QiniuGroup;
import com.jeeplus.web.entities.system.SysOssEntity;
import com.jeeplus.web.oss.CloudStorageConfig;
import com.jeeplus.web.oss.OSSFactory;
import com.jeeplus.web.service.system.SysConfigService;
import com.jeeplus.web.service.system.SysOssService;
import com.jeeplus.web.util.PageUtils;
import com.jeeplus.web.util.ValidatorUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 文件上传
 * @author:yuzp17311
 * @version:v1.0
 * @date: 2017-03-30 16:24.
 */
@Controller
@RequestMapping("sys/oss")
public class SysOssController {

    @Autowired
    private SysOssService sysOssService;
    @Autowired
    private SysConfigService sysConfigService;

    private final static String KEY = ConfigConstant.CLOUD_STORAGE_CONFIG_KEY;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("sys:oss:all")
    public R list(@RequestParam Map<String, Object> params){
        //查询列表数据
        Query query = new Query(params);
        List<SysOssEntity> sysOssList = sysOssService.queryList(query);
        int total = sysOssService.queryTotal(query);

        PageUtils pageUtil = new PageUtils(sysOssList, total, query.getLimit(), query.getPage());

        return R.ok().put("page", pageUtil);
    }


    /**
     * 云存储配置信息
     */
    @RequestMapping("/config")
    @RequiresPermissions("sys:oss:all")
    public R config(){
        CloudStorageConfig config = null;
        try {
            config = sysConfigService.getConfigObject(KEY, CloudStorageConfig.class);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return R.ok().put("config", config);
    }


    /**
     * 保存云存储配置信息
     */
    @RequestMapping("/saveConfig")
    @RequiresPermissions("sys:oss:all")
    public R saveConfig(@RequestBody CloudStorageConfig config){
        //校验类型
        ValidatorUtils.validateEntity(config);

        if(config.getType() == Constant.CloudService.QINIU.getValue()){
            //校验七牛数据
            ValidatorUtils.validateEntity(config, QiniuGroup.class);
        }else if(config.getType() == Constant.CloudService.ALIYUN.getValue()){
            //校验阿里云数据
            ValidatorUtils.validateEntity(config, AliyunGroup.class);
        }else if(config.getType() == Constant.CloudService.QCLOUD.getValue()){
            //校验腾讯云数据
            ValidatorUtils.validateEntity(config, QcloudGroup.class);
        }


        sysConfigService.updateValueByKey(KEY, JSON.toJSONString(config));


        return R.ok();
    }


    /**
     * 上传文件
     */
    @RequestMapping("/upload")
    @RequiresPermissions("sys:oss:all")
    public R upload(@RequestParam("file") MultipartFile file) throws Exception {
        if (file.isEmpty()) {
            throw new RRException("上传文件不能为空");
        }

        //上传文件
        String url = OSSFactory.build().upload(file.getBytes());

        //保存文件信息
        SysOssEntity ossEntity = new SysOssEntity();
        ossEntity.setUrl(url);
        ossEntity.setCreateDate(new Date());
        sysOssService.save(ossEntity);

        return R.ok().put("url", url);
    }


    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("sys:oss:all")
    public R delete(@RequestBody Long[] ids){
        sysOssService.deleteBatch(ids);

        return R.ok();
    }

}
