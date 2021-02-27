package com.ruoyi.common.constant;

/**
 * @program: ruoyi
 * @description: Jwt常量
 * @author: 杨有旺
 * @create: 2019-09-04 14:32
 **/
public interface JwtConstants {


    String AUTH_HEADER = "Authorization";

    String SECRET = "defaultSecret";

    int EXPIRATION = 604800;

    String JWT_SEPARATOR = "Bearer ";
}
