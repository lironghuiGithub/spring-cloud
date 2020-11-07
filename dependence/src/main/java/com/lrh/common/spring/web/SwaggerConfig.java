package com.lrh.common.spring.web;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import springfox.documentation.builders.*;
import springfox.documentation.oas.annotations.EnableOpenApi;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Parameter;
import springfox.documentation.service.RequestParameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;

/**
 * @author lironghui
 * @version 1.0
 * @date 2019/11/3 11:18
 */
@Configuration
@EnableOpenApi
@ConditionalOnExpression("${swagger.enable:true}")
public class SwaggerConfig {
    @Value("${swagger.basePackage}")
    private String swaggerBasePackage;

    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.OAS_30)
                .apiInfo(apiInfo())
                .select()
//                .apis(RequestHandlerSelectors.withClassAnnotation(Controller.class))
//                .apis(RequestHandlerSelectors.withMethodAnnotation(RequestMapping.class)
                .apis(RequestHandlerSelectors.basePackage(swaggerBasePackage))
                .paths(PathSelectors.any())
                .build()
                .globalRequestParameters(headersBuilder())
                ;
    }


    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("Spring Boot中使用Swagger2构建RESTful APIs")
                .description("lrh project")
                .termsOfServiceUrl("")
                .version("1.0").build();
    }

    private List<RequestParameter> headersBuilder() {
        List<RequestParameter> headers = new ArrayList<>();
        RequestParameterBuilder uidHeader = new RequestParameterBuilder();
        uidHeader.name("uid").description("user id").in("header").required(false); //header中的ticket参数非必填，传空也可以
        RequestParameterBuilder tokenHeader = new RequestParameterBuilder();
        tokenHeader.name("token").description("user token").in("header").required(false); //header中的ticket参数非必填，传空也可以
        headers.add(uidHeader.build());
        headers.add(tokenHeader.build());
        return headers;
    }

}
