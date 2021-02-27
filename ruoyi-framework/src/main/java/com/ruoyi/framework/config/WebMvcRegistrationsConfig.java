package com.ruoyi.framework.config;

import org.springframework.boot.autoconfigure.web.servlet.WebMvcRegistrations;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

/**
 * @author yangyouwang
 * @title: WebMvcRegistrationsConfig
 * @projectName ruoyi
 * @description: API版本配置
 * @date 2020/10/24下午11:50
 */
@Configuration
public class WebMvcRegistrationsConfig implements WebMvcRegistrations {

    @Override
    public RequestMappingHandlerMapping getRequestMappingHandlerMapping() {
        return new ApiVersioningRequestMappingHandlerMapping();
    }
}