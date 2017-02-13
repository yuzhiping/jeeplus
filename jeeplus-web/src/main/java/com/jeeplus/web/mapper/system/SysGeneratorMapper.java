package com.jeeplus.web.mapper.system;

import java.util.List;
import java.util.Map;

/**
 * 代码生成器
 * @author:yuzp17311
 * @version:v1.0
 * @date: 2016-12-30 17:41.
 */
public interface SysGeneratorMapper {

    List<Map<String, Object>> queryList(Map<String, Object> map);

    int queryTotal(Map<String, Object> map);

    Map<String, String> queryTable(String tableName);

    List<Map<String, String>> queryColumns(String tableName);

}
