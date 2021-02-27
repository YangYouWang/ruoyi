package com.ruoyi.common.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author yangyouwang
 * @title: SmsProperties
 * @projectName RuoYi
 * @description: 短信配置
 * @date 2020/7/31下午11:12
 */
@Data
@Component
@ConfigurationProperties(prefix = SmsProperties.PREFIX)
public class SmsProperties {

    public static final String PREFIX = "aliyun.sms";
    /**
     * 创建的accesskey中的AccessKey ID
     */
    private String accessKeyId;
    /**
     * 创建的accesskey中的Access Key Secret
     */
    private String accessKeySecret;
    /**
     * 签名管理中的签名
     */
    private String signName;
}
