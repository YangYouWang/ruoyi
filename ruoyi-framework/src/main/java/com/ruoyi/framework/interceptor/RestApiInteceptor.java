package com.ruoyi.framework.interceptor;

import com.ruoyi.common.annotation.PassToken;
import com.ruoyi.common.constant.JwtConstants;
import com.ruoyi.common.utils.JwtTokenUtil;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.framework.web.exception.UnauthorizedException;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

/**
 * @program: ruoyi
 * @description: Rest Api接口鉴权
 * @author: 杨有旺
 * @create: 2019-09-04 14:31
 **/
@Component
public class RestApiInteceptor extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        if (handler instanceof org.springframework.web.servlet.resource.ResourceHttpRequestHandler) {
            return true;
        }
        return check(request, response, handler);
    }

    private boolean check(HttpServletRequest request, HttpServletResponse response, Object handler) {

        HandlerMethod handlerMethod = (HandlerMethod)handler ;
        Method method = handlerMethod.getMethod() ;
        //检查是否有passtoken注释，有则跳过认证
        if (method.isAnnotationPresent(PassToken.class)) {
            PassToken passToken = method.getAnnotation(PassToken.class);
            if (passToken.required()) {
                return true ;
            }
        }
        // 获取 HTTP HEAD 中的 TOKEN
        String authorization = request.getHeader(JwtConstants.AUTH_HEADER);
        // 校验 TOKEN
        if (StringUtils.isNotNull(authorization)  && authorization.startsWith(JwtConstants.JWT_SEPARATOR)) {
            boolean flag = JwtTokenUtil.checkJWT(authorization) ;
            if (flag) {
                JwtTokenUtil.parseJWT(authorization);
                return true ;
            }
        }
        throw new UnauthorizedException();
    }

}
