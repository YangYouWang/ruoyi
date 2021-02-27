package com.ruoyi.common.properties;


import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.io.Serializable;

/**
 * oss配置
 * @author yangyouwang
 */
@Data
@Component
@ConfigurationProperties(prefix = OSSProperties.PREFIX)
public class OSSProperties implements Serializable {

    public static final String PREFIX = "aliyun.oss";

    private static final long serialVersionUID = -119396871324982279L;

    /**
     * 阿里云 oss 站点
     */
    private String endPoint;

    /**
     * 阿里云 oss 资源访问 url
     */
    private String url;

    /**
     * 阿里云 oss 公钥
     */
    private String accessKeyId;

    /**
     * 阿里云 oss 私钥
     */
    private String accessKeySecret;

    /**
     * 阿里云 oss 文件根目录
     */
    private String bucketName;
}
