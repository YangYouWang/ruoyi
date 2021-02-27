package com.ruoyi.framework.config;

import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.mvc.condition.RequestCondition;

import javax.servlet.http.HttpServletRequest;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author yangyouwang
 * @title: ApiVersionCondition
 * @projectName ruoyi
 * @description:
 * @date 2020/10/24下午11:47
 */
@Log
@Slf4j
public class ApiVersionCondition implements RequestCondition<ApiVersionCondition> {

    /**
     * 从URL中提取版本部分，例如：[v0-9]
     */
    private final static Pattern VERSION_PREFIX_PATTERN = Pattern.compile("v(\\d+)/");

    private int apiVersion;

    public ApiVersionCondition(int apiVersion) {
        log.info("ApiVersionCondition apiVersion ==> " + apiVersion);
        log.info("ApiVersionCondition Init...");
        this.apiVersion = apiVersion;
    }

    @Override
    public ApiVersionCondition combine(ApiVersionCondition other) {
        // 采用最后定义优先原则，则方法上的定义覆盖类上面的定义
        return new ApiVersionCondition(other.apiVersion);
    }

    @Override
    public ApiVersionCondition getMatchingCondition(HttpServletRequest request) {
        Matcher matcher = VERSION_PREFIX_PATTERN.matcher(request.getRequestURI());
        if (matcher.find()) {
            Integer version = Integer.valueOf(matcher.group(1));
            // 当应用比配置更大的版本号时，它将采取提取
            if (version >= this.apiVersion) {
                return this;
            }
        }
        return null;
    }

    @Override
    public int compareTo(ApiVersionCondition other, HttpServletRequest request) {
        return other.apiVersion - this.apiVersion;
    }

    public int getApiVersion() {
        return apiVersion;
    }
}
