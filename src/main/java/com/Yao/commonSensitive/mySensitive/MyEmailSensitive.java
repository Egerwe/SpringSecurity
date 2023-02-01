package com.Yao.commonSensitive.mySensitive;

import com.Yao.commonSensitive.myStrategy.MyEmailStrategy;
import com.fasterxml.jackson.annotation.JacksonAnnotationsInside;

import java.lang.annotation.*;

/**
 * Author Yjw
 * 2023/2/1 10:59
 */
@Documented
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@CommonSensitive(strategy = MyEmailStrategy.class)
@JacksonAnnotationsInside
public @interface MyEmailSensitive {
}
