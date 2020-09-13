package com.lrh.common.spring.web;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.*;
import springfox.documentation.oas.annotations.EnableOpenApi;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Parameter;
import springfox.documentation.service.ParameterType;
import springfox.documentation.service.RequestParameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import springfox.documentation.swagger2.annotations.EnableSwagger2WebFlux;

import java.util.ArrayList;
import java.util.List;

/**
 * @author lironghui
 * @version 1.0
 * @date 2019/11/3 11:18
 */
//@Configuration
//@EnableSwagger2
//@EnableOpenApi
//@ConditionalOnExpression("${swagger.enable:true}")
public class SwaggerConfig {
    @Value("${swagger.basePackage}")
    private String basePackage;

    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.OAS_30)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage(basePackage))
                .paths(PathSelectors.any()).build()
                .globalRequestParameters(globalHeaders());
    }

    /**
     * 请求透信息信息
     *
     * @return
     */
    private List<RequestParameter> globalHeaders() {
        List<RequestParameter> headers = new ArrayList<>();
        //header中的ticket参数非必填，传空也可以
        RequestParameterBuilder userIdHeader = new RequestParameterBuilder();
        userIdHeader.name("uid").description("userId").in(ParameterType.HEADER).required(false);
        RequestParameterBuilder userToken = new RequestParameterBuilder();
        userIdHeader.name("token").description("user identity token").in(ParameterType.HEADER).required(false);
        headers.add(userIdHeader.build());
        headers.add(userToken.build());
        return headers;
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("Spring Boot中使用Swagger2构建RESTful APIs")
                .description("lrh project")
                .termsOfServiceUrl("https://spring.io/projects/spring-cloud")
                .version("1.0").build();
    }
}
