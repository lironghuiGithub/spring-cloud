package com.lrh.common.spring.web.handler;

import org.springframework.context.i18n.LocaleContext;
import org.springframework.context.i18n.SimpleLocaleContext;
import org.springframework.util.StringUtils;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.i18n.LocaleContextResolver;

import java.util.Locale;

/**
 * @version 1.0
 * @auther lironghui
 * @date 2020/8/30
 */
public class LocalResolver implements LocaleContextResolver {
    @Override
    public LocaleContext resolveLocaleContext(ServerWebExchange exchange) {
        String local = exchange.getRequest().getHeaders().getFirst("local");
        if (StringUtils.isEmpty(local)) {
            return new SimpleLocaleContext(Locale.getDefault());
        } else {
            String[] split = local.split("_");
            if (split.length == 2) {
                return new SimpleLocaleContext(new Locale(split[0], split[1]));
            }
        }
        return new SimpleLocaleContext(Locale.getDefault());
    }

    @Override
    public void setLocaleContext(ServerWebExchange exchange, LocaleContext localeContext) {
        throw new UnsupportedOperationException("NOT SUPPORTED");
    }
}
