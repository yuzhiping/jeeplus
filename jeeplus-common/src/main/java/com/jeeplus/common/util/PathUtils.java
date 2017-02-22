package com.jeeplus.common.util;

import java.io.File;

/**
 * @author:yuzp17311
 * @version:v1.0
 * @date: 2017-02-22 14:12.
 */
public class PathUtils {

    /**
     * 得到WEB-INF的路径
     * @return
     */
    public static String getWebInfPath(){
        String path=PathUtils.class.getClassLoader().getResource("").getPath();
        File f = new File(path);
        return f.getParentFile().getPath();
    }

}
