package com.Yao.commonSensitive.mySensitive;

import com.Yao.commonSensitive.MyDefaultStrategy;
import com.Yao.commonSensitive.MySensitiveInfoSerialize;
import com.Yao.commonSensitive.MyStrategy;
import com.fasterxml.jackson.annotation.JacksonAnnotationsInside;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Author Yjw
 * 2023/2/1 10:47
 */
@Retention(RetentionPolicy.RUNTIME)
@JacksonAnnotationsInside
@JsonSerialize(using = MySensitiveInfoSerialize.class)
@Inherited
public @interface CommonSensitive {
    /**
     * 脱敏策略
     *
     * @return
     */
    Class<? extends MyStrategy> strategy() default MyDefaultStrategy.class;
}
