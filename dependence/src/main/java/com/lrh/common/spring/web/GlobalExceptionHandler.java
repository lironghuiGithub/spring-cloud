package com.lrh.common.spring.web;

import com.lrh.common.spring.R;
import com.lrh.common.spring.common.GlobalErrorCode;
import com.lrh.common.spring.web.exception.ResultException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

/**
 * @author lironghui
 * @version 1.0
 * @date 2019/11/3 14:13
 */
@ControllerAdvice
public class GlobalExceptionHandler {
    Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ResponseBody
    @ExceptionHandler(ResultException.class)
    public R handleException(ResultException e, HttpServletRequest request) {
        String code = e.getCode();
        String msg = SpringtUtil.getMessage(e.getCode(), e.getObjects(), LocaleContextHolder.getLocale());
        return R.fail(code, msg);
    }

    @ResponseBody
    @ExceptionHandler(Exception.class)
    public R handleException(Exception e, HttpServletRequest request) {
        logger.error("resp error url={}", request.getRequestURI());
        String code = GlobalErrorCode.UNKNOWN_ERROR;
        String msg = e.getMessage();
        return R.fail(code, msg);
    }
}
