package com.lrh.common.spring.util;

import org.springframework.util.Base64Utils;

/**
 * @version 1.0
 * @auther lironghui
 * @date 2020/8/30
 */
public class ThreadUtil {
    public void sleep(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
