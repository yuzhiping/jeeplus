package com.jeeplus.web.services.impl;

import com.alibaba.fastjson.JSON;
import com.jeeplus.web.entities.SysConfigEntity;
import com.jeeplus.web.mapper.SysConfigMapper;
import com.jeeplus.web.services.SysConfigService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @author:yuzp17311
 * @version:v1.0
 * @date: 2016-12-12 10:01.
 */
@Service("sysConfigService")
public class SysConfigServiceImpl implements SysConfigService {

    @Autowired
    private SysConfigMapper sysConfigMapper;

    /**
     * 保存配置信息
     *
     * @param config
     */
    @Override
    public void save(SysConfigEntity config) {
        sysConfigMapper.save(config);
    }

    /**
     * 更新配置信息
     *
     * @param config
     */
    @Override
    public void update(SysConfigEntity config) {
        sysConfigMapper.update(config);
    }

    /**
     * 根据key，更新value
     *
     * @param key
     * @param value
     */
    @Override
    public void updateValueByKey(String key, String value) {
        sysConfigMapper.updateValueByKey(key,value);
    }

    /**
     * 删除配置信息
     *
     * @param ids
     */
    @Override
    public void deleteBatch(Long[] ids) {
        sysConfigMapper.deleteBatch(ids);
    }

    /**
     * 获取List列表
     *
     * @param map
     */
    @Override
    public List<SysConfigEntity> queryList(Map<String, Object> map) {
        return sysConfigMapper.queryList(map);
    }

    /**
     * 获取总记录数
     *
     * @param map
     */
    @Override
    public int queryTotal(Map<String, Object> map) {
        return sysConfigMapper.queryTotal(map);
    }

    @Override
    public SysConfigEntity queryObject(Long id) {
        return sysConfigMapper.queryObject(id);
    }

    /**
     * 根据key，获取配置的value值
     *
     * @param key          key
     * @param defaultValue 缺省值
     */
    @Override
    public String getValue(String key, String defaultValue) {
        String value = sysConfigMapper.queryByKey(key);
        if(StringUtils.isBlank(value)){
            return defaultValue;
        }
        return value;
    }

    /**
     * 根据key，获取value的Object对象
     *
     * @param key   key
     * @param clazz Object对象
     */
    @Override
    public <T> T getConfigObject(String key, Class<T> clazz) throws Exception {
        String value = getValue(key, null);
        if(StringUtils.isNotBlank(value)){
            return JSON.parseObject(value, clazz);
        }

        return clazz.newInstance();
    }
}
