package com.guihang.medicalbackend.config;

import com.guihang.medicalbackend.commons.SwaggerProperties;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Knife4jConfig {
    @Autowired
    SwaggerProperties swaggerProperties;

    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI()
                .info(new Info() // 基本信息配置
                        .title(swaggerProperties.getTitle()) // 标题
                        .description(swaggerProperties.getDescription()) // 描述Api接口文档的基本信息
                        .version(swaggerProperties.getVersion()) // 版本
                        .termsOfService(swaggerProperties.getTermsOfServiceUrl())
                        // 设置OpenAPI文档的联系信息，包括联系人姓名为"patrick"，邮箱为"patrick@gmail.com"。
                        .contact(new Contact().name(swaggerProperties.getName()).email(swaggerProperties.getEmail()))
                        // 设置OpenAPI文档的许可证信息，包括许可证名称为"Apache 2.0"，许可证URL为"http://springdoc.org"。
                        .license(new License().name("Apache 2.0").url("https://springdoc.org"))
                );
    }

    /**
     * 用户模块的API分组
     */
    @Bean
    public GroupedOpenApi accountApi() { // 创建了一个api接口的分组
        return GroupedOpenApi.builder()
                .group("用户模块") // 分组名称
                .pathsToMatch("/user/**") // 接口请求路径规则
                .build();
    }

    /**
     * 权限模块的API分组
     */
    @Bean
    public GroupedOpenApi permissionApi() { // 创建了一个api接口的分组
        return GroupedOpenApi.builder()
                .group("权限模块") // 分组名称
                .pathsToMatch("/permission/**") // 接口请求路径规则
                .build();
    }

    /**
     * 医药公司模块的API分组
     */
    @Bean
    public GroupedOpenApi drugCompanyApi() { // 创建了一个api接口的分组
        return GroupedOpenApi.builder()
                .group("医药公司模块") // 分组名称
                .pathsToMatch("/drug/**") // 接口请求路径规则
                .build();
    }


}
