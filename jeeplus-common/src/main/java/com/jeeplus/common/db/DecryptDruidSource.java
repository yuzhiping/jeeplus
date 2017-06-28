package com.jeeplus.common.db;

import com.alibaba.druid.pool.DruidDataSource;
import com.jeeplus.common.util.EncryptUtils;

/**
 * 使用AES加密数据库连接的密码，避免在配置文件中使用明文
 * @author:yuzp17311
 * @version:v1.0
 * @date: 2017-06-28 20:30.
 */
public class DecryptDruidSource extends DruidDataSource {

    public void setPassword() {
        try {
            password= EncryptUtils.aesDecrypt(password,EncryptUtils.defaultKey);
        }catch (Exception e){
            throw new RuntimeException("数据库密码未按正确格式加密");
        }
        super.setPassword(password);
    }


}
