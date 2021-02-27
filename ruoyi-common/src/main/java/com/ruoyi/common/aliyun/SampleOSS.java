package com.ruoyi.common.aliyun;

import com.aliyun.oss.ClientConfiguration;
import com.aliyun.oss.OSSClient;
import com.aliyun.oss.common.auth.DefaultCredentialProvider;
import com.ruoyi.common.properties.OSSProperties;
import com.ruoyi.common.utils.spring.SpringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;

/**
 * 上传文件到oss
 * @author yangyouwang
 */
public class SampleOSS {

    private SampleOSS(){}

    /**
     * oss 工具客户端
     */
    private volatile static OSSClient ossClient = null;

    /**
     * 上传文件至阿里云 OSS
     * 文件上传成功,返回文件完整访问路径
     * 文件上传失败,返回 null
     *
     * @param file 待上传文件
     * @param fileDir 文件保存目录
     * @return oss 中的相对文件路径
     */
    public static String upload(MultipartFile file, String fileDir){
        OSSProperties ossProperties = SpringUtils.getBean(OSSProperties.class);
        initOSS(ossProperties);
        StringBuilder fileUrl = new StringBuilder();
        try {
            String suffix = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf('.'));
            String fileName = System.currentTimeMillis() + "-" + UUID.randomUUID().toString().substring(0,18) + suffix;
            if (!fileDir.endsWith("/")) {
                fileDir = fileDir.concat("/");
            }
            fileUrl = fileUrl.append(fileDir + fileName);

            ossClient.putObject(ossProperties.getBucketName(), fileUrl.toString(), file.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        fileUrl = fileUrl.insert(0,ossProperties.getUrl());
        return fileUrl.toString();
    }

    /**
     * 初始化 oss 客户端
     * @param ossProperties
     * @return
     */
    private static OSSClient initOSS(OSSProperties ossProperties) {
        if (ossClient == null ) {
            synchronized (SampleOSS.class) {
                if (ossClient == null) {
                    ossClient = new OSSClient(ossProperties.getEndPoint(),
                            new DefaultCredentialProvider(ossProperties.getAccessKeyId(), ossProperties.getAccessKeySecret()),
                            new ClientConfiguration());
                }
            }
        }
        return ossClient;
    }
}