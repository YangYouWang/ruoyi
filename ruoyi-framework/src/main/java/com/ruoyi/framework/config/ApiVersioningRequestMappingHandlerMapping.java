package com.ruoyi.framework.config;

import com.ruoyi.common.annotation.ApiVersion;
import lombok.extern.java.Log;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.web.servlet.mvc.condition.RequestCondition;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import java.lang.reflect.Method;

/**
 * @author yangyouwang
 * @title: ApiVersioningRequestMappingHandlerMapping
 * @projectName ruoyi
 * @description:
 * @date 2020/10/24下午11:49
 */
@Log
public class ApiVersioningRequestMappingHandlerMapping extends RequestMappingHandlerMapping {

    public ApiVersioningRequestMappingHandlerMapping() {
        log.info("ApiVersioningRequestMappingHandlerMapping Init...");
    }

    @Override
    protected RequestCondition<ApiVersionCondition> getCustomTypeCondition(Class<?> handlerType) {

        ApiVersion apiVersion = AnnotationUtils.findAnnotation(handlerType, ApiVersion.class);
        return createCondition(apiVersion);
    }

    @Override
    protected RequestCondition<ApiVersionCondition> getCustomMethodCondition(Method method) {
        ApiVersion apiVersion = AnnotationUtils.findAnnotation(method, ApiVersion.class);
        return createCondition(apiVersion);
    }

    private RequestCondition<ApiVersionCondition> createCondition(ApiVersion apiVersion) {
        return apiVersion == null ? null : new ApiVersionCondition(apiVersion.value());
    }
}