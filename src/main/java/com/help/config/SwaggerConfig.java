package com.help.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.context.annotation.Profile;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @Configuration ：表明这是一个配置类，让Spring来加载该类配置
 * @EnableSwagger2 ：通过@EnableSwagger2注解来启用Swagger2，表示开启Swagger
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {

	@Bean
    public Docket buildDocket_api() {
        return new Docket(DocumentationType.SWAGGER_2)
        		//分组
                .groupName("help-api")
                //是否启用swagger
                .enable(true)
                .apiInfo(buildApiInf())
                .select()
                //指定controller路径
                .apis(RequestHandlerSelectors.basePackage("com.help.modules.api"))
                //指明要扫描哪些路径
                .paths(PathSelectors.any())
                .build();
	}

    @Bean
    public Docket buildDocket_user() {
        return new Docket(DocumentationType.SWAGGER_2)
                //分组
                .groupName("help-user")
                //是否启用swagger
                .enable(true)
                .apiInfo(buildApiInf())
                .select()
                //指定controller路径
                .apis(RequestHandlerSelectors.basePackage("com.help.modules.o2o"))
                //指明要扫描哪些路径
                .paths(PathSelectors.any())
                .build();
    }


    @Bean
    public Docket buildDocket_sys() {
        return new Docket(DocumentationType.SWAGGER_2)
                //分组
                .groupName("help-sys")
                //是否启用swagger
                .enable(true)
                .apiInfo(buildApiInf())
                .select()
                //指定controller路径
                .apis(RequestHandlerSelectors.basePackage("com.help.modules.sys"))
                //指明要扫描哪些路径
                .paths(PathSelectors.any())
                .build();
    }

    @Bean
    public Docket buildDocket_wechat() {
        return new Docket(DocumentationType.SWAGGER_2)
                //分组
                .groupName("help-wechat")
                //是否启用swagger
                .enable(true)
                .apiInfo(buildApiInf())
                .select()
                //指定controller路径
                .apis(RequestHandlerSelectors.basePackage("com.help.modules.wechat"))
                //指明要扫描哪些路径
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo buildApiInf() {
        return new ApiInfoBuilder()
                .title("help-server API Documentation") //文档标题
                .description("校园互助系统API文档") //文档描述
                .version("v1.0")
                .contact(new Contact("LMH", "https://blog.csdn.net/qq_16665045", "limhua23@foxmail.com"))//作者信息
                .build();
    }
}
