package com.xhh.rbac.common.handler;

import com.xhh.rbac.common.entity.RbacResponse;
import com.xhh.rbac.common.exception.RbacAuthException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@Slf4j
public class BaseExceptionHandler {

    @ExceptionHandler(value = Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public RbacResponse handleException(Exception e) {
        log.error("系统内部异常，异常信息", e);
        return new RbacResponse().message("系统内部异常");
    }

    @ExceptionHandler(value = RbacAuthException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public RbacResponse handleFebsAuthException(RbacAuthException e) {
        log.error("系统错误", e);
        return new RbacResponse().message(e.getMessage());
    }

    @ExceptionHandler(value = AccessDeniedException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public RbacResponse handleAccessDeniedException() {
        return new RbacResponse().message("没有权限访问该资源");
    }
}