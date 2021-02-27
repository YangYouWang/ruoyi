package com.ruoyi.common.context;


import org.apache.commons.collections.map.HashedMap;

import java.util.Map;

/**
 * 获取userId上下文环境
 * @author yangyouwang
 */
public class ApiContext {

    private static final Map<String, Object> CONTEXT = new HashedMap(16);

    public static void setUserId(String userId) {
        CONTEXT.put("userId", userId);
    }

    public static String getUserId() {
        if (CONTEXT.containsKey("userId")) {
            return CONTEXT.get("userId").toString();
        }
        return null;
    }
}