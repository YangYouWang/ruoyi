package com.ruoyi.common.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author yangyouwang
 * @title: MailConfig
 * @projectName enroll
 * @description: MailConfig
 * @date 2020/7/6下午11:00
 */

@Data
@Component
@ConfigurationProperties(prefix = MailProperties.PREFIX)
public class MailProperties {

    public static final String PREFIX = "aliyun.mail";

    /**
     * accessKeyId
     */
    private String accessKeyId;

    /**
     *   accessKeySecret
     */
    private String accessKeySecret;

    /**
     * 发信地址
     */
    private String accountName;

    /**
     *区域Id
     */
    private String regionId;

    /**
     *发信人昵称
     */
    private String sendPersonName;

    /**
     *发信地址代码 1
     */
    private Integer addressType;

    /**
     *控制台创建的标签
     */
    private String tagName;

    /**
     *回信地址
     */
    private boolean replyToAddress;

    /**
     *目标地址
     */
    private String toAddress;

}
