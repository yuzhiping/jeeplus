package com.jeeplus.web.oss;

import com.jeeplus.web.common.constant.ConfigConstant;
import com.jeeplus.web.common.constant.Constant;
import com.jeeplus.web.service.system.SysConfigService;
import com.jeeplus.web.util.SpringContextUtils;

/**
 * 文件上传Factory
 *
 * @author:yuzp17311
 * @version:v1.0
 * @date: 2017-03-30 13:50.
 */
public final class OSSFactory {

    private static SysConfigService sysConfigService;

    static {
        OSSFactory.sysConfigService = (SysConfigService) SpringContextUtils.getBean("sysConfigService");
    }

    public static CloudStorageService build() throws Exception {
        //获取云存储配置信息
        CloudStorageConfig config = sysConfigService.getConfigObject(ConfigConstant.CLOUD_STORAGE_CONFIG_KEY, CloudStorageConfig.class);

        if (config.getType() == Constant.CloudService.QINIU.getValue()) {
            return new QiniuCloudStorageService(config);
        } else if (config.getType() == Constant.CloudService.ALIYUN.getValue()) {
            return new AliyunCloudStorageService(config);
        } else if (config.getType() == Constant.CloudService.QCLOUD.getValue()) {
            return new QcloudCloudStorageService(config);
        }

        return null;
    }

}
