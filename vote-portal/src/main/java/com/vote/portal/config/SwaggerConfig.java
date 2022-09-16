package com.vote.portal.config;

import com.vote.common.config.BaseSwaggerConfig;
import com.vote.common.config.SwaggerProperties;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * swagger 普通用户portal接口技术文档
 * @author qinxuening
 * @date 2022/9/14 11:30
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig extends BaseSwaggerConfig {
    @Override
    public SwaggerProperties swaggerProperties() {
        return SwaggerProperties.builder()
                .apiBasePackage("com.vote.portal.controller")
                .title("简易投票系统客户端")
                .description("前台普通用户portal相关接口文档")
                .contactName("qinxuening")
                .version("1.0")
                .enableSecurity(true)
                .build();
    }
}
