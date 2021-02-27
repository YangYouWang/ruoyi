package com.ruoyi.framework.config;

import com.ruoyi.framework.interceptor.RestApiInteceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.*;
import com.ruoyi.common.config.Global;
import com.ruoyi.common.constant.Constants;
import com.ruoyi.framework.interceptor.RepeatSubmitInterceptor;

/**
 * 通用配置
 * 
 * @author ruoyi
 */
@Configuration
public class ResourcesConfig implements WebMvcConfigurer
{
    /**
     * 首页地址
     */
    @Value("${shiro.user.indexUrl}")
    private String indexUrl;

    @Autowired
    private RepeatSubmitInterceptor repeatSubmitInterceptor;

    @Autowired
    private RestApiInteceptor restApiInteceptor;

    /**
     * 默认首页的设置，当输入域名是可以自动跳转到默认指定的网页
     */
    @Override
    public void addViewControllers(ViewControllerRegistry registry)
    {
        registry.addViewController("/").setViewName("forward:" + indexUrl);
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry)
    {
        /** 本地文件上传路径 */
        registry.addResourceHandler(Constants.RESOURCE_PREFIX + "/**").addResourceLocations("file:" + Global.getProfile() + "/");

        /** swagger配置 */
        registry.addResourceHandler("swagger-ui.html").addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
    }

    /**
     * 自定义拦截规则
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry)
    {
        registry.addInterceptor(repeatSubmitInterceptor).addPathPatterns("/**");
        registry.addInterceptor(restApiInteceptor).addPathPatterns("/api/**");
    }

    /**
     * 跨域
     */
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("*")
                .allowCredentials(true)
                .allowedMethods("GET", "POST", "DELETE", "PUT","PATCH")
                .maxAge(3600);
    }
}