package com.lrh.gateway.util;

import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.StopWatch;
import org.springframework.util.AntPathMatcher;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @version 1.0
 * @auther lironghui
 * @date 2020/8/16
 */
public class AntPathUtil {
    private static final AntPathMatcher pathMatcher = new AntPathMatcher();

    /**
     * 验证url是否匹配
     *
     * @param pattern
     * @param path
     * @return
     */
    public static boolean match(String pattern, String path) {
        return pathMatcher.match(pattern, path);
    }

    /**
     * 验证url是否匹配任意一项
     *
     * @param patterns
     * @param path
     * @return
     */
    public static boolean matchAny(List<String> patterns, String path) {
        if (patterns == null || patterns.size() == 0) {
            return false;
        }
        for (String pattern : patterns) {
            if (pathMatcher.match(pattern, path)) {
                return true;
            }
        }
        return false;
    }
}
