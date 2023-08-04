package com.dq.util;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author: Bonree
 * @Date: 2021-4-25 16:34
 * @Desc: jackson工具类
 */
public class JacksonUtils {
    
    private static final Logger LOG = LoggerFactory.getLogger(JacksonUtils.class);

    private static final ObjectMapper MAPPER = new ObjectMapper();
    /*** json字段是下划线 《==》 对象字段名为驼峰，相互转换的mapper */
    private static final ObjectMapper UNDER_CAMEL_MAPPER = new ObjectMapper();

    static {
        MAPPER.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        MAPPER.registerModule(new JavaTimeModule());
        UNDER_CAMEL_MAPPER.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        UNDER_CAMEL_MAPPER.setPropertyNamingStrategy(PropertyNamingStrategies.SNAKE_CASE);
    }

    JacksonUtils() {
    }
    
    public static ObjectMapper getMAPPER() {
        return MAPPER;
    }

    /***
     * 把对象转换为json字符串，不忽略值为null
     * @param obj 对象
     * @return json字符串
     */
    public static String obj2JsonString(Object obj) {
        try {
            return MAPPER.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return "";
        }
    }

    /***
     * 把对象转换为json字符串
     * @param obj 对象
     * @return json字符串
     */
    public static String toStringCusTime(Object obj) {
        try {
            return getObjectMapperCusTime1().writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            LOG.error("parse object to json string error", e);
            return "";
        }
    }

    /***
     * 将object类型转为map类型
     * @param object json格式的object
     * @return Map<String, Object>
     */
    public static Map<String, Object> obj2Map(Object object) {
        return jsonString2Map(obj2JsonString(object));
    }

    /***
     * 转换为JSON字符串,忽略空值
     * @param obj 对象
     * @return json字符串
     */
    public static String obj2JsonStringIgnoreNull(Object obj) {
        ObjectMapper mapper = new ObjectMapper();
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        mapper.registerModule(new JavaTimeModule());
        try {
            return mapper.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            LOG.error("parse object to string ignore null error", e);
            return "";
        }
    }

    /***
     * 将json字符串转换为指定的java对象
     * @param jsonString json字符串
     * @param clazz java对象的class
     * @param <T> 泛型
     * @return java对象
     */
    public static <T> T jsonString2Pojo(String jsonString, Class<T> clazz) {
        MAPPER.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
        try {
            return MAPPER.readValue(jsonString, clazz);
        } catch (JsonProcessingException e) {
            LOG.error("parse json string to pojo error", e);
            return (T) clazz;
        }
    }

    /**
     *
     * @param jsonString
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T> T jsonString2PojoCamel(String jsonString, Class<T> clazz) {
        UNDER_CAMEL_MAPPER.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
        try {
            return UNDER_CAMEL_MAPPER.readValue(jsonString, clazz);
        } catch (JsonProcessingException e) {
            LOG.error("parse json string to pojo error", e);
            return (T) clazz;
        }
    }

    /**
     *
     * @param object
     * @return
     */
    public static String pojoCamel2jsonString(Object object) {
        UNDER_CAMEL_MAPPER.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        UNDER_CAMEL_MAPPER.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
        try {
            return UNDER_CAMEL_MAPPER.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            LOG.error("parse object to string camel error", e);
            return "";
        }
    }

    /***
     * json字符串转换为map结构
     * @param jsonString 字符串
     * @return map结构
     */
    public static Map<String, Object> jsonString2Map(String jsonString) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
            return mapper.readValue(jsonString, Map.class);
        } catch (JsonProcessingException e) {
            LOG.error("parse json string to map error", e);
            return new HashMap<>(4);
        }
    }

    /**
     * 字符串转换为 Map<String, T>
     *
     * @return map结构
     */
    public static <T> Map<String, T> json2Map(String jsonString, Class<T> clazz) {
        Map<String, Map<String, Object>> map;
        try {
            map = MAPPER.readValue(jsonString,
                    new TypeReference<Map<String, Map<String, Object>>>() {
                    });
        } catch (JsonProcessingException e) {
            LOG.error("parse json string to map pojo error", e);
            return new HashMap<>(4);
        }
        Map<String, T> result = new HashMap<>(4);
        for (Map.Entry<String, Map<String, Object>> entry : map.entrySet()) {
            result.put(entry.getKey(), map2pojo(entry.getValue(), clazz));
        }
        return result;
    }

    /**
     * 深度转换 JSON 成 Map
     *
     * @param jsonString 嵌套json结构
     * @return map形式
     */
    public static Map<String, Object> jsonString2MapDeeply(String jsonString) {
        return json2MapRecursion(jsonString, MAPPER);
    }

    /**
     * 把 JSON 解析成 List，如果 List 内部的元素存在 jsonString，继续解析
     *
     * @param json
     * @param mapper 解析工具
     * @return
     * @throws Exception
     */
    private static List<Object> json2ListRecursion(String json, ObjectMapper mapper) {
        try {
            return mapper.readValue(json, List.class);
        } catch (JsonProcessingException e) {
            LOG.error("parse json string to list error", e);
        }
        return new ArrayList<>();
    }

    /***
     * 把 JSON 解析成 Map，如果 Map 内部的 Value 存在 jsonString，继续解析
     * @param json json字符串
     * @param mapper 解析类
     * @return map结构
     */
    private static Map<String, Object> json2MapRecursion(String json, ObjectMapper mapper) {
        try {
            Map<String, Object> map = mapper.readValue(json, Map.class);
            for (Map.Entry<String, Object> entry : map.entrySet()) {
                Object obj = entry.getValue();
                if (obj instanceof String) {
                    String str = ((String) obj);
                    if (str.startsWith("[")) {
                        List<?> list = json2ListRecursion(str, mapper);
                        map.put(entry.getKey(), list);
                    } else if (str.startsWith("{")) {
                        Map<String, Object> mapRecursion = json2MapRecursion(str, mapper);
                        map.put(entry.getKey(), mapRecursion);
                    }
                }
            }
            return map;
        } catch (Exception e) {
            LOG.error("parse json string to map recursion error", e);
            return new HashMap<>(2);
        }
    }

    /***
     * 将JSON数组转换为集合
     * @param jsonArrayStr 数组格式字符串
     * @param clazz 类
     * @param <T> 泛型
     * @return 对象
     */
    public static <T> List<T> jsonArrayStr2List(String jsonArrayStr, Class<T> clazz) {
        JavaType javaType = getCollectionType(ArrayList.class, clazz);
        try {
            return MAPPER.readValue(jsonArrayStr == null ? new ArrayList<>().toString() : jsonArrayStr, javaType);
        } catch (JsonProcessingException e) {
            LOG.error("parse json string to list error", e);
        }
        return new ArrayList<>();
    }

    /***
     * 获取泛型的 Collection Type
     * @param collectionClass 泛型的Collection
     * @param elementClasses  元素类
     * @return JavaType Java类型
     */
    public static JavaType getCollectionType(Class<?> collectionClass, Class<?>... elementClasses) {
        return MAPPER.getTypeFactory().constructParametricType(collectionClass, elementClasses);
    }

    /***
     * 将map转换为指定的java对象
     * @param map map格式
     * @param clazz 类
     * @param <T> 泛型
     * @return java对象
     */
    public static <T> T map2pojo(Map<String, Object> map, Class<T> clazz) {
        return MAPPER.convertValue(map, clazz);
    }

    /***
     * 将map转换成json
     * @param map map形式
     * @return json形式
     */
    public static String map2JsonString(Map<String, Object> map) {
        try {
            return MAPPER.writeValueAsString(map);
        } catch (Exception e) {
            LOG.error("parse map to json string error", e);
        }
        return "";
    }

    /***
     * 将JSON对象转换为JavaBean
     * @param obj 对象
     * @param clazz 类
     * @param <T>泛型
     * @return java对象
     */
    public static <T> T obj2Pojo(Object obj, Class<T> clazz) {
        return MAPPER.convertValue(obj, clazz);
    }

    /**
     * 获取yyyy-MM-dd HH:mm:ss 格式序列化时间的 ObjectMapper
     *
     * @return
     */
    public static ObjectMapper getObjectMapperCusTime1() {
        return getObjectMapperCusTime("yyyy-MM-dd HH:mm:ss");
    }

    /**
     * 获取指定 序列化时间格式 的ObjectMapper
     *
     * @param pattern 要序列化时间的格式
     * @return
     */
    public static ObjectMapper getObjectMapperCusTime(String pattern) {
        ObjectMapper objectMapper = new ObjectMapper();
        JavaTimeModule javaTimeModule = new JavaTimeModule();
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(pattern);
        javaTimeModule.addSerializer(LocalDateTime.class, new LocalDateTimeSerializer(dateTimeFormatter));
        objectMapper.registerModule(javaTimeModule);
        return objectMapper;
    }

    public static <T> T parseResult(String result) throws JsonProcessingException {
        return MAPPER.readValue(result, new TypeReference<T>() {});
    }
}
