package com.pdd.components.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * swagger2 配置类
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket createRestApi(){
        return new Docket(DocumentationType.SWAGGER_2)
                // 调用apiInfo方法,创建一个ApiInfo实例,里面是展示在文档页面信息内容
                .apiInfo(apiInfo())
                .select()
                // 控制暴露的接口
                // @ApiIgnore 控制不暴露的接口
                .apis(RequestHandlerSelectors.basePackage("com.pdd.components.controller"))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo(){
        return new ApiInfoBuilder()
                // 页面标题
                .title("API文档")
                // 条款地址
                .termsOfServiceUrl("http://www.vostroi.com")
                // 联系方式
                .contact(new Contact("vostroi" , "http://www.vostroi.com" , "little_baby_fat@163.com"))
                // 版本
                .version("RELEASED 1.0")
                // 描述
                .description("API")
                .build();
    }
}
