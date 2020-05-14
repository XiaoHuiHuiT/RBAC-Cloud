package com.xhh.rbac.common.handler;

import com.xhh.rbac.common.entity.RbacResponse;
import com.xhh.rbac.common.utils.RbacUtil;
import org.springframework.http.MediaType;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 　　* @描述: 用于处理403类型异常
 * 　　* @参数 ${tags}
 * 　　* @返回值 ${return_type}
 * 　　* @throws
 * 　　* @作者: 小灰灰
 * 　　* @时间 2020-05-14 21:12
 */
public class RbacAccessDeniedHandler implements AccessDeniedHandler {

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException {
        RbacResponse febsResponse = new RbacResponse();
        RbacUtil.makeResponse(
                response, MediaType.APPLICATION_JSON_UTF8_VALUE,
                // 响应码为HttpServletResponse.SC_FORBIDDEN，即403
                HttpServletResponse.SC_FORBIDDEN, febsResponse.message("没有权限访问该资源"));
    }
}