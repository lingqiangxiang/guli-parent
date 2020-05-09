package com.lqx.config;

import com.google.common.base.Predicates;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author: Bruce
 * @description: swagger2配置类
 * @date: Created in 2020-04-23 17:22
 * @modified by:
 */
@Configuration
@EnableSwagger2
public class Swagger2 {

    @Bean
    public Docket webApiConfig(){
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("webApi")
                .apiInfo(webApiInfo())
                .select()
                //不显示的页面
                .paths(Predicates.not(PathSelectors.regex("/admin/.*")))
                .paths(Predicates.not(PathSelectors.regex("/error.*")))
                .build();
    }

//    访问地址：http://localhost:端口号/swagger-ui.html
//    访问地址：http://localhost:8111/swagger-ui.html
    private ApiInfo webApiInfo(){
        return new ApiInfoBuilder()
                .title("网站-老师API文档")
                .description("本文档描述了老师微服务接口定义")
                .version("1.0")
//                描述信息
//                .contact(new Contact("Bruce", "http://lqx.com", "@qq.com"))
                .build();
    }

}
