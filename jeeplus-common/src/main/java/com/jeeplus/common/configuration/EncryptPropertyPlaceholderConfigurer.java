package com.jeeplus.common.configuration;

import com.jeeplus.common.util.DesUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;

/**
 * SpringMVC实现解密接口
 * 需要覆盖convertProperty方法，encryptPropNames存储的是需要解密的属性
 * Created by yuzp17311 on 2016/8/31.
 */
public class EncryptPropertyPlaceholderConfigurer extends PropertyPlaceholderConfigurer {

    private static Logger LOG= LoggerFactory.
            getLogger(EncryptPropertyPlaceholderConfigurer.class);

    private String[] encryptPropNames={"jdbc.username","jdbc.password","mailserver.google.username","mailserver.google.password"};

    /**
     * 判断是否是需要加密的属性
     * @param propertyName
     * @return
     */
    private boolean isEncryptProp(String propertyName){
        for (String encryptName:encryptPropNames){
            if (encryptName.equals(propertyName))
            {
                return true;
            }
        }
        return false;
    }

    @Override
    protected String convertProperty(String propertyName,String propertyValue){
        //如果在加密属性中发现该名单
        if(isEncryptProp(propertyName)){
            String decryptValue= DesUtils.aesDecrypt(propertyValue,DesUtils.defaultKey);
            LOG.info("解密后的字符串为："+decryptValue);
            return  decryptValue;
        }else
            return propertyValue;
    }










}
