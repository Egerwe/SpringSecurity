package com.Yao.commonSensitive;

import com.Yao.utils.SensitiveUtils;

/**
 * Author Yjw
 * 2023/2/1 10:42
 */
public class MyDefaultStrategy implements MyStrategy{
    @Override
    public String desensitizationByPattern(String source) {
        return SensitiveUtils.commonSensitive(source);
    }
}
