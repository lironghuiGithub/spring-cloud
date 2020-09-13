package com.lrh.upload.model;

import org.springframework.util.AntPathMatcher;

/**
 * @version 1.0
 * @auther lironghui
 * @date 2020/8/16
 */
public class Auth {
    public static void main(String[] args) {
        AntPathMatcher pathMatcher = new AntPathMatcher();

        pathMatcher.setCachePatterns(true);
        pathMatcher.setCaseSensitive(true);
        pathMatcher.setTrimTokens(true);
        pathMatcher.setPathSeparator("/");

        String url="**/sys/conf/*";
        String url2="sys/conf/list/ss";
        System.out.println(pathMatcher.match(url,url2));
    }
}
