package com.rdlts.enigma.ddd.spring.utils;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONReader;
import com.alibaba.fastjson2.JSONWriter;
import org.springframework.stereotype.Component;

/**
 * FastJsonUtils
 * FastJson默认使用的是Setter方法注入值的，在注入值对象的时候这个不符合值对象不能有setter的要求，因此需要使用JSONReader.Feature.FieldBased
 *
 * @see com.alibaba.fastjson2.JSONReader.Feature#FieldBased
 * @author wangjialong
 * @since 2025/12/4 09:00
 */
@Component
public class FastJsonUtils {


    /**
     * prettyJson
     * @param t Object
     * @return String
     */
    public static String prettyJson(Object t) {
        return JSON.toJSONString(t,
                JSONWriter.Feature.PrettyFormat,
                JSONWriter.Feature.WriteMapNullValue,
                JSONWriter.Feature.WriteNullListAsEmpty,
                JSONWriter.Feature.WriteNullStringAsEmpty);
    }

    /**
     * 默认使用FieldBased解析
     * @param jsonString String
     * @return T
     * @param <T>
     */
    public static <T> T parseObject(String jsonString, Class<T> clazz) {
        return JSON.parseObject(jsonString, clazz, JSONReader.Feature.FieldBased);
    }
}
