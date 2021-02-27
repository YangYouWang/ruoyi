package com.ruoyi.web.core.config;

import com.ruoyi.common.annotation.ApiVersion;
import com.ruoyi.common.constant.ApiVersionConstant;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.ruoyi.common.config.Global;
import io.swagger.annotations.ApiOperation;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Swagger2的接口配置
 * 
 * @author ruoyi
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {
    /**
     * 创建API
     */
    @Bean
    public Docket createRestApi()
    {
        return new Docket(DocumentationType.SWAGGER_2)
                // 用来创建该API的基本信息，展示在文档的页面中（自定义展示的信息）
                .apiInfo(apiInfo())
                // 设置哪些接口暴露给Swagger展示
                .select()
                // 扫描所有有注解的api，用这种方式更灵活
                .apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class))
                // 扫描所有 .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                .build()
                .globalOperationParameters(globalOperation());
    }

    /**
     * 添加摘要信息
     */
    private ApiInfo apiInfo()
    {
        // 用ApiInfoBuilder进行定制
        return new ApiInfoBuilder()
                // 设置标题
                .title("标题：微商城系统_接口文档")
                // 描述
                .description("描述：微商城接口文档")
                // 作者信息
                .contact(new Contact(Global.getName(), null, null))
                // 版本
                .version("版本号:" + Global.getVersion())
                .build();
    }

    @Bean
    public Docket appV1(){
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .groupName(ApiVersionConstant.SWAGGER_API_V1)
                .select()
                .apis(input -> {
                    ApiVersion apiVersion = input.getHandlerMethod().getMethodAnnotation(ApiVersion.class);
                    if(apiVersion != null && Arrays.asList(apiVersion.group()).contains(ApiVersionConstant.SWAGGER_API_V1)){
                        return true;
                    }
                    return false;
                })//controller路径
                .paths(PathSelectors.any())
                .build()
                .globalOperationParameters(globalOperation());
    }


    private List<Parameter> globalOperation(){
        List<Parameter> pars = new ArrayList<>();
        ParameterBuilder tokenPar = new ParameterBuilder();
        tokenPar.name("Authorization").description("token").modelRef(new ModelRef("string")).parameterType("header").required(false).build();
        pars.add(tokenPar.build());
        ParameterBuilder versionPar = new ParameterBuilder();
        versionPar.name("version").description("版本").modelRef(new ModelRef("string")).parameterType("path").defaultValue("v1").required(true).build();
        pars.add(versionPar.build());
        return pars;
    }
}
