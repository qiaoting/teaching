package com.teaching.common.exception.user;

import com.teaching.common.exception.base.BaseException;

/**
 * 用户信息异常类
 * 
 * @author qiaoting
 */
public class UserException extends BaseException
{
    private static final long serialVersionUID = 1L;

    public UserException(String code, Object[] args)
    {
        super("user", code, args, null);
    }
}
