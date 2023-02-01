package com.Yao.commonSensitive.myStrategy;

import com.Yao.commonSensitive.MyStrategy;
import com.Yao.utils.SensitiveUtils;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;

/**
 * Author Yjw
 * 2023/2/1 10:56
 */
public class MyEmailStrategy implements MyStrategy {
    @Override
    public String desensitizationByPattern(String source) {
        if (StringUtils.isNotBlank(source)) {
            int s = source.length();
            int ss = source.indexOf("@");
            int sss = s - ss;
            return source.replaceAll("\\S{" + ss + "}(\\S{" + sss + "})",
                    SensitiveUtils.getStars(ss)+"$1");
        } else {
            return source;
        }
    }
}
