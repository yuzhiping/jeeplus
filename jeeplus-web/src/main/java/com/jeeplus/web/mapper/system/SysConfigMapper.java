package com.jeeplus.web.mapper.system;

import com.jeeplus.web.entities.system.SysConfigEntity;
import com.jeeplus.web.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

/**
 * 系统配置信息
 * @author:yuzp17311
 * @version:v1.0
 * @date: 2016-12-12 9:39.
 */
public interface SysConfigMapper extends BaseMapper<SysConfigEntity> {


    /**

     * 根据key，查询value

     */
    String queryByKey(String paramKey);

    /**

     * 根据key，更新value

     */
    int updateValueByKey(@Param("key") String key, @Param("value") String value);

}
