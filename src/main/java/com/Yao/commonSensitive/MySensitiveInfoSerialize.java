package com.Yao.commonSensitive;

import com.Yao.commonSensitive.mySensitive.CommonSensitive;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.ContextualSerializer;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Objects;

/**
 * Author Yjw
 * 2023/2/1 10:50
 */
@Slf4j
@NoArgsConstructor
public class MySensitiveInfoSerialize extends JsonSerializer<String> implements ContextualSerializer {

    public MyStrategy myStrategy;
    private MySensitiveInfoSerialize(MyStrategy myStrategy) {
        this.myStrategy = myStrategy;
    }

    @Override
    public void serialize(String s, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeString(myStrategy.desensitizationByPattern(s));
    }

    @Override
    public JsonSerializer<?> createContextual(SerializerProvider serializerProvider, BeanProperty beanProperty) throws JsonMappingException {
        if (beanProperty != null) {
            // 非 String 类直接跳过
            if (Objects.equals(beanProperty.getType().getRawClass(), String.class)) {
                CommonSensitive sensitiveInfo = beanProperty.getAnnotation(CommonSensitive.class);
                if (sensitiveInfo == null) {
                    sensitiveInfo = beanProperty.getContextAnnotation(CommonSensitive.class);
                }
                if (sensitiveInfo != null) {
                    Class<? extends MyStrategy> clazz = sensitiveInfo.strategy();
                    // 如果能得到注解，就将注解的 value 传入 SensitiveInfoSerialize
                    try {
                        return new MySensitiveInfoSerialize(clazz.getDeclaredConstructor().newInstance());
                    } catch (InstantiationException | IllegalAccessException | InvocationTargetException |
                            NoSuchMethodException e) {
                        throw new RuntimeException(e);
                    }
                }
                return serializerProvider.findValueSerializer(beanProperty.getType(), beanProperty);
            }
        }
        return serializerProvider.findNullValueSerializer(null);
    }
}
