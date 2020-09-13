package com.lrh.gateway.common;

import com.lrh.common.spring.common.GlobalErrorCode;

/**
 * @version 1.0
 * @auther lironghui
 * @date 2020/8/16
 */
public interface ErrorCode extends GlobalErrorCode {
    /**
     * 身份信息无效用户不存在
     */
    int USER_IDENTITY_INVALID_ERROR = 2000;
    /**
     * 身份信息过期
     */
    int USER_LOGIN_EXPIRE_ERROR = 2001;
    /**
     * 没有权限
     */
    int USER_AUTH_INVALID_ERROR = 2002;
}
