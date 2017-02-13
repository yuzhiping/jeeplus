package com.jeeplus.common.util;



import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by yuzp17311 on 2016/9/8.
 */
public final class  JacksonMapper {
    private static final Logger logger = LoggerFactory.getLogger(JacksonMapper.class);

    /**
     * jackson 的映射类, 将类型信息一并转换
     */
    public static final ObjectMapper classMapper=new ObjectMapper();

    /**
     * jackson 的映射类, 忽略类型信息
     */
    public static final ObjectMapper jsonMapper = new ObjectMapper();

    static {
        /** 将java对象的类的信息加上 */
        classMapper.enableDefaultTyping();
        /**
         * 忽略的序列化设置。1、json串中存在的属性(且java对象中不存在的属性值)，
         * 在转换为java对象时忽略。2、java对象存在(且没有实际的属性或值) 转换为json时忽略信息。
         */
        classMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        classMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS,false);
    }

    /**
     * 从jsonNode对象中取值,如果未找到返回空字符串
     */
    public static final String getValue(JsonNode node, String key) {
        return node.get(key) != null ? node.get(key).asText() : "";
    }

    /**
     * 从jsonNode对象中查找对应的键值,如果未找到返回空字符串
     */
    public static final String findValue(JsonNode node, String key) {
        return node.findValue(key) != null ? node.findValue(key).asText() : "";
    }

    /**
     * 从jsonNode对象中查找对应的键的list值的集合,如果未找到返回空List
     */
    public static final List<String> findValues(JsonNode node, String key) {
        return node.findValuesAsText(key) != null ? node.findValuesAsText(key) : new ArrayList<String>(0);
    }

    /**
     * 转换json字符串 包括类型信息,如果转换出现异常返回空字符串
     */
    public static final String toCJsonString(Object obj) {
        String json = "";
        try {
            json = classMapper.writeValueAsString(obj);
        } catch (Exception e) {
            logger.error(obj.getClass().getName() + "对象转换包含类型信息的json出错！", e);
        }
        return json;
    }

    /**
     * 转换json字符串 不包括类型信息,如果转换出现异常返回空字符串
     */
    public static final String toJsonString(Object obj) {
        String json = "";
        try {
            json = jsonMapper.writeValueAsString(obj);
        } catch (Exception e) {
            logger.error(obj.getClass().getName() + "对象转换为json出错！", e);
        }
        return json;
    }

    /**
     * 转换json字符串 包括类型信息,如果转换出现异常返回长度为0的字节数组
     */
    public static final byte[] toBytes(Object obj) {
        byte[] jsonBytes = new byte[0];
        try {
            jsonBytes = classMapper.writeValueAsBytes(obj);
        } catch (Exception e) {
            logger.error(obj.getClass().getName() + "对象转换为byte出错！", e);
        }
        return jsonBytes;
    }

    /**
     * 将json字符串转换为具体的类型对象,如果转换出现异常返回null
     */
    @SuppressWarnings("unchecked")
    public static final <T> T jsonToClass(String json, Class<T> tClass) {
        if (json == null || "".equals(json.trim())) return null;


        T req = null;
        try {
            req = tClass == String.class ? (T) json : tClass == JsonNode.class ? (T) toJsonNode(json) : Map.class.isAssignableFrom(tClass) ? jsonMapper.readValue(json, tClass) : classMapper.readValue(json, tClass);
        } catch (Exception e) {
            logger.error("json字符串转换java对象：" + tClass.getName() + "出错！", e);
        }
        return req;
    }

    /**
     * 将JsonNode 转换为具体的类型对象,如果转换出现异常返回null
     */
    @SuppressWarnings("unchecked")
    public static final <T> T jsonNodeToClass(JsonNode jsonNode, Class<T> tClass) {
        if (jsonNode == null) return null;

        T req = null;
        try {
            req = tClass == String.class ? (T) jsonNode.toString() :
                    tClass == JsonNode.class ? (T) jsonNode : Map.class.isAssignableFrom(tClass) ? jsonMapper.convertValue(jsonNode, tClass) : classMapper.convertValue(jsonNode, tClass);
        } catch (Exception e) {
            logger.error("jsonNode对象转换java对象：" + tClass.getName() + "出错！", e);
        }
        return req;
    }

    /**
     * 将json 转换为JsonNode对象,如果转换出现异常返回null
     */
    public static final JsonNode toJsonNode(String json) {
        if (json == null || "".equals(json.trim())) return null;
        JsonNode node = null;
        try {
            node = jsonMapper.readTree(json);
        } catch (IOException e) {
            logger.error("json字符串：" + json + ", 转换jsonNode对象出错！", e);
        }
        return node;
    }

    /**
     * 将InputStream 转换为JsonNode对象
     */
    public static final JsonNode readJsonNode(InputStream in) throws IOException {
        return jsonMapper.readTree(read(in));
    }

    /**
     * 包装request的输入流(且必须包装，转换速度有很大的区别)，必须设置UTF-8编码，不然中文会乱码
     */
    public static final BufferedReader read(InputStream in) throws UnsupportedEncodingException {
        return new BufferedReader(new InputStreamReader(in, "UTF-8"));
    }

}
