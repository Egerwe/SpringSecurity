package com.Yao.utils;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;

/**
 * Author Yjw
 * 2023/2/1 10:00
 */
public class SensitiveUtils {
    public static final String SYMBOL_STAR = "*";
    /**
     * 通用脱敏
     * 字段长度 大于 10 前三后四明文
     * 字段长度 小于 4 无明文
     * 字段长度 大其他 后四明文
     *
     * @param sensitiveValue 敏感值
     * @return 脱敏值
     */
    public static String commonSensitive(String sensitiveValue) {
        if (StringUtils.isBlank(sensitiveValue)) {
            return sensitiveValue;
        }
        if (sensitiveValue.length() > 10) {
            int s = sensitiveValue.length() - 7;
            return sensitiveValue.replaceAll("(\\w{3})\\w{" + s + "}(\\w{4})", "$1" + getStars(s) + "$2");
        } else if (sensitiveValue.length() < 4) {
            return getStars(sensitiveValue.length());
        } else {
            int s = sensitiveValue.length() - 4;
            return sensitiveValue.replaceAll("\\w{" + s + "}(\\w{4})", getStars(s) + "$1");
        }
    }
    /**
     * 判断是否是脱敏值
     * 是脱敏值 返回 true
     * 不是脱敏值 返回 false
     *
     * @param value 输入值
     * @return 返回结果
     */
    public static boolean simpleInvalidSensitive(String value) {
        if (StringUtils.isBlank(value)) {
            return false;
        }
        return value.contains(SYMBOL_STAR);
    }
    public static String getStars(int s) {
        String stars = StringUtils.EMPTY;
        for (int i = 0; i < s; i++) {
            stars += SYMBOL_STAR;
        }
        return stars;
    }
}
