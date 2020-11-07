package com.lrh.common.spring.web;

import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.LocaleResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Locale;

/**
 * @author lironghui
 * @version 1.0
 * @date 2019/11/10 10:49
 */
public class GlobalLocaleResolver implements LocaleResolver {
    @Override
    public Locale resolveLocale(HttpServletRequest request) {
        String l = request.getHeader("l");
        if (StringUtils.isEmpty(l)) {
            return Locale.getDefault();
        } else {
            String[] split = l.split("_");
            return new Locale(split[0], split[1]);
        }
    }

    @Override
    public void setLocale(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Locale locale) {

    }
}
