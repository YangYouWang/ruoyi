package com.ruoyi.framework.web.exception;

/**
 * @program: ruoyi
 * @description: UnauthorizedException
 * @author: 杨有旺
 * @create: 2019-09-04 15:33
 **/
public class UnauthorizedException extends RuntimeException {

    private static final long serialVersionUID = 3885400551304383736L;

    public UnauthorizedException(String msg)
    {
        super(msg);
    }

    public UnauthorizedException()
    {
        super();
    }
}
