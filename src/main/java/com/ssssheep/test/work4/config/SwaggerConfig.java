package com.ssssheep.test.work4.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;


/**
 * Created By Intellij IDEA
 *
 * @author Xinrui Yu
 * @date 2022/4/6 17:22 星期三
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {
    private static final String DEFAULT_PATH = "/doc";
    private static final String splitor = ";";
    @Bean
    public Docket createRestApi(){
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.clevesheep.controller"))
                .paths(PathSelectors.any())
                .build()
                .securitySchemes(securitySchemes())
                .securityContexts(Collections.singletonList(securityContexts()));
    }
    private ApiInfo apiInfo(){
        return new ApiInfoBuilder()
                .title("医院报障系统")
                .description("接口文档")
                .contact(new Contact("Xinrui Yu","http://localhost:8080/doc.html","54535282@qq.com"))
                .version("1.0")
                .build();
    }

    private List<ApiKey> securitySchemes(){
        ArrayList<ApiKey> keys = new ArrayList<>();
        ApiKey apiKey = new ApiKey("Authorization", "Authorization", "Header");
        keys.add(apiKey);
        return keys;
    }

    private SecurityContext securityContexts(){
        return SecurityContext.builder()
                .securityReferences(defaultAuth())
                .forPaths(PathSelectors.any())
                .build();
    }
    private List<SecurityReference> defaultAuth() {
        AuthorizationScope authorizationScope = new AuthorizationScope("xxx", "描述信息");
        AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
        authorizationScopes[0] = authorizationScope;
        return Arrays.asList(new SecurityReference("Authorization", authorizationScopes));
    }
}