package com.lrh.common.spring.web;

import com.lrh.common.spring.web.handler.LocalResolver;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.config.DelegatingWebFluxConfiguration;
import org.springframework.web.server.i18n.LocaleContextResolver;

/**
 * @version 1.0
 * @auther lironghui
 * @date 2020/8/30
 */
@Configuration
public class LocalSupportConfig extends DelegatingWebFluxConfiguration {
    @Override
    protected LocaleContextResolver createLocaleContextResolver() {
        return new LocalResolver();
    }

}
