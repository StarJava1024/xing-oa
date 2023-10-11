package com.xing.common.config.swagger3;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

/**
 * @Description: Swagger3
 * @Author: Wang Xing
 * @Date: 16:18 2023/7/24
 */

@Configuration
// @EnableOpenApi
public class SwaggerConfig {

    @Bean
    public Docket createRestApi(){
        // Docket: 摘要对象，通过对象配置 描述文件的信息
        Docket docket = new Docket(DocumentationType.OAS_30);
        docket.apiInfo(apiInfo())
                // select()：返回ApiSelectorBuilder对象，通过对象调用build()可以创建Docket对象
                .select()
                // 指定要扫描/维护接口文档的包（否则就全部扫描）
                .apis(RequestHandlerSelectors.basePackage("com.xing"))
                // 路径过滤：该Docket-UI展示时，只展示指定路径下的接口文档(any表示都展示)
                .paths(PathSelectors.any())
                .build();
        return docket;
    }

    // 接口文档的概要信息，返回ApiInfo对象
    private ApiInfo apiInfo(){
        ApiInfo apiInfo =  new ApiInfoBuilder()
                .title("OA系统接口文档")          // 标题
                .description("一个简单明了的接口信息文档")   // 简单描述
                .version("V1.0.0")              // 版本
                .termsOfServiceUrl("/admin")         // url接口路径前缀
                .contact(new Contact("xing","","18709822197@163.com"))
                .license("笔兴洽谈室--博客园")  // 协议
                .licenseUrl("https://www.cnblogs.com/CrayonXiaoxing/")   // 协议url
                .build();
        return apiInfo;
    }

}