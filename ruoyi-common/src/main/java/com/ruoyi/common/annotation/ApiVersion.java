package com.ruoyi.common.annotation;

import java.lang.annotation.*;
/**
 * 接口版本管理注解
 * @author 杨有旺
 * @Date 2020-10-25
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ApiVersion {


    /**
     * version
     *
     * @return 版本号
     */
    int value();

    /**
     * 接口版本号(对应swagger中的group)
     * @return String[]
     */
    String[] group();
}
