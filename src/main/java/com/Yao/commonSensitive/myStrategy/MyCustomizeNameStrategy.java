package com.Yao.commonSensitive.myStrategy;

import com.Yao.commonSensitive.MyStrategy;
import com.Yao.utils.SensitiveUtils;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;

/**
 * 序列化姓名，保留姓
 *
 * Author Yjw
 * 2023/2/1 10:53
 */

public class MyCustomizeNameStrategy implements MyStrategy {
    @Override
    public String desensitizationByPattern(String source) {
        if (StringUtils.isNotBlank(source)) {
            int s = source.length();
            int ss = source.lastIndexOf(".");
            if (ss == -1) {
                ss = 1;
                int sss = s - ss;
                return source.replaceAll("(\\S{" + ss + "})\\S{" + sss + "}",
                        "$1" + SensitiveUtils.getStars(sss));
            } else {
                int sss = s - ss;
                return source.replaceAll("\\S{" + ss + "}(\\S{" + sss + "})",
                        SensitiveUtils.getStars(ss) + "$1");
            }
        } else {
            return source;
        }
    }
}
