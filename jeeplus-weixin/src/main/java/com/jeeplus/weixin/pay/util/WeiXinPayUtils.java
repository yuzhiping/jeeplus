package com.jeeplus.weixin.pay.util;

import com.thoughtworks.xstream.XStream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.lang.reflect.Field;
import java.util.Map;

/**
 * @author:yuzp17311
 * @version:v1.0
 * @date: 2017-01-05 18:54.
 */
public class WeiXinPayUtils {

    private static final Logger LOGGER= LoggerFactory.getLogger(WeiXinPayUtils.class);


    /**
     * 通过反射的方式遍历对象的属性和属性值，方便调试
     * @param o 要遍历的对象
     * @throws Exception
     */
    public static void reflact(Object o) throws Exception{
        Class clazz=o.getClass();
        Field [] fields=clazz.getDeclaredFields();
        for (int i=0;i<fields.length;i++){
            Field field=fields[i];
            field.setAccessible(true);
            WeiXinPayUtils.log(field.getName() + " -> " + field.get(o));
        }
    }


    public static byte[] readInput(InputStream inputStream) throws IOException{
        ByteArrayOutputStream outputStream=new ByteArrayOutputStream();
        int len=0;
        byte [] buffer=new byte[1024];
        while ((len=inputStream.read(buffer))>0){
            outputStream.write(buffer,0,len);
        }
        outputStream.close();
        inputStream.close();
        return  outputStream.toByteArray();
    }


    public static String inputStreamToString(InputStream inputStream) throws IOException{
        ByteArrayOutputStream outputStream=new ByteArrayOutputStream();
        int i;
        while ((i=inputStream.read())!=-1){
            outputStream.write(i);
        }
        return outputStream.toString();
    }

    public static InputStream getStringStream(String inputString) throws UnsupportedEncodingException{
        ByteArrayInputStream inputStream=null;
        if(inputString!=null&&!inputString.trim().equals("")){
            inputStream=new ByteArrayInputStream(inputString.getBytes("UTF-8"));
        }
        return inputStream;
    }


    public static Object getObjectFromXML(String xmlString,Class clazz){
        //将从API返回的XML数据映射到Java对象
        XStream xStream=new XStream();
        xStream.alias("xml",clazz);
        //暂时忽略掉一些新增的字段
        xStream.ignoreUnknownElements();
        return xStream.fromXML(xmlString);
    }


    public static String getStringFromMap(Map<String, Object> map, String key, String defaultValue) {
        if (key == "" || key == null) {
            return defaultValue;
        }
        String result = (String) map.get(key);
        if (result == null) {
            return defaultValue;
        } else {
            return result;
        }
    }


    public static int getIntFromMap(Map<String, Object> map, String key) {
        if (key == "" || key == null) {
            return 0;
        }
        if (map.get(key) == null) {
            return 0;
        }
        return Integer.parseInt((String) map.get(key));
    }


    /**
     * 打log接口
     * @param log 要打印的log字符串
     * @return 返回log
     */
    public static String log(Object log){
        LOGGER.info(log.toString());
        return log.toString();
    }

    /**
     * 读取本地的xml数据，一般用来自测用
     * @param localPath 本地xml文件路径
     * @return 读到的xml字符串
     */
    public static String getLocalXMLString(String localPath) throws IOException {
        return WeiXinPayUtils.inputStreamToString(WeiXinPayUtils.class.getResourceAsStream(localPath));
    }


}
