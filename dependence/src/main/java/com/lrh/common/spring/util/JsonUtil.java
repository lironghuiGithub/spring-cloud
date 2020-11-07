package com.lrh.common.spring.util;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.sun.jndi.toolkit.url.Uri;
import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.*;

/**
 * @version 1.0
 * @auther lironghui
 * @date 2020/8/30
 */
public class JsonUtil {
    private static final ObjectMapper objectMapper = new ObjectMapper();

    static {
        // 对象的所有字段全部列入，还是其他的选项，可以忽略null等
        objectMapper.setSerializationInclusion(JsonInclude.Include.ALWAYS);
        // 设置Date类型的序列化及反序列化格式
//        objectMapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
        // 忽略空Bean转json的错误
        objectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
        // 忽略未知属性，防止json字符串中存在，java对象中不存在对应属性的情况出现错误
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        //使用javaArray 反序列化jsonArray
        objectMapper.configure(DeserializationFeature.USE_JAVA_ARRAY_FOR_JSON_ARRAY, true);
        // 注册一个时间序列化及反序列化的处理模块，用于解决jdk8中localDateTime等的序列化问题
        objectMapper.registerModule(new JavaTimeModule());
    }

    /*
     * 获取objecMapper 执行自定义操作
     * @return
     */
    public static ObjectMapper getObjectMapper() {
        return objectMapper;
    }

    /**
     * json字符串转JsonNode
     *
     * @param json
     * @return
     */
    public static JsonNode parseObject(String json) {
        try {
            return objectMapper.readTree(json);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * json文件转json
     *
     * @param jsonFile
     * @return
     */
    public static JsonNode parseObject(File jsonFile) {
        try {
            return objectMapper.readTree(jsonFile);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * json字符串转java对象
     *
     * @param json
     * @param clazz A.class  A[].class
     * @param <T>
     * @return
     */
    public static <T> T parseObject(String json, Class<T> clazz) {
        try {
            return objectMapper.readValue(json, clazz);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Json文件转java对象
     *
     * @param jsonFile
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T> T parseObject(File jsonFile, Class<T> clazz) {
        try {
            return objectMapper.readValue(jsonFile, clazz);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * json字符串转泛型对象
     *
     * @param json
     * @param typeReference new TypeReference<List<A>>(){};new TypeReference<Map<String,String>>(){};
     * @param <T>
     * @return
     */
    public static <T> T parseObject(String json, TypeReference<T> typeReference) {
        try {
            return objectMapper.readValue(json, typeReference);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * json文件转泛型对象
     *
     * @param jsonFile
     * @param typeReference new TypeReference<List<A>>(){};new TypeReference<Map<String,String>>(){};
     * @param <T>
     * @return
     */
    public static <T> T parseObject(File jsonFile, TypeReference<T> typeReference) {
        try {
            return objectMapper.readValue(jsonFile, typeReference);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * json字符串转数组
     *
     * @param json
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T> List<T> parseArray(String json, Class<T> clazz) {
        try {
            JavaType javaType = objectMapper.getTypeFactory().constructParametricType(List.class, clazz);
            return objectMapper.readValue(json, javaType);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * json文件转集合
     *
     * @param jsonFile
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T> List<T> parseArray(File jsonFile, Class<T> clazz) {
        try {
            JavaType javaType = objectMapper.getTypeFactory().constructParametricType(List.class, clazz);
            return objectMapper.readValue(jsonFile, javaType);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    /**
     * java对象转JSON String
     *
     * @param obj
     * @return
     */
    public static String toJsonString(Object obj) {
        try {
            return objectMapper.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
