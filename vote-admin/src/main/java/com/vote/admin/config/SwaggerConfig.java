package com.vote.admin.config;

import com.vote.common.config.BaseSwaggerConfig;
import com.vote.common.config.SwaggerProperties;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * swagger 系统管理admin接口技术文档
 * @author qinxuening
 * @date 2022/9/13 18:22
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig extends BaseSwaggerConfig {
    @Override
    public SwaggerProperties swaggerProperties() {
        return SwaggerProperties.builder()
                .apiBasePackage("com.vote.admin.controller")
                .title("简易投票系统后台")
                .description("系统管理admin相关接口文档")
                .contactName("qinxuening")
                .version("1.0")
                .enableSecurity(true)
                .build();
    }
}
