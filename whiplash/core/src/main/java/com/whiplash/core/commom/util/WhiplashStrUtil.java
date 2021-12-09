package com.whiplash.core.commom.util;

import cn.hutool.core.util.StrUtil;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

/**
 * @author Administrator
 * @date 2021/12/7 17:26
 * @projectName whiplash
 * @title: WhiplashStrUtil
 * @description:
 */
@Slf4j
public class WhiplashStrUtil {

    /**
     * 按 多 分隔符  分隔字符串
     * @param source
     * @param regexs
     * @return
     */
    public static List<String> splitString(String source, String[] regexs) {
        long startTime = System.currentTimeMillis();
        List<String> target = Lists.newArrayList();
        try {
            if (StrUtil.isNotEmpty(source) && null != regexs && regexs.length > 0) {
                String[] tmp = source.split(regexs[0], -1);

                target = Lists.newArrayList(tmp);

                if (regexs.length > 1) {
                    List<String> sl = null;
                    String[] ts = null;
                    for (int i = 1; i < regexs.length; i++) {
                        sl = Lists.newArrayList();
                        for (String t : target) {
                            ts = t.split(regexs[i], -1);

                            sl.addAll(Lists.newArrayList(ts));
                        }
                        target = Lists.newArrayList(sl);
                    }
                }
            }
        } catch (Exception e) {
            log.error("拆分字符串异常：", e);
        } finally {
            log.debug("拆分字符串 {} >>> {} 耗时:{}", source, regexs, (System.currentTimeMillis() - startTime));
        }
        return target;
    }

}



