package com.xhh.rbac.common.handler;

import com.alibaba.fastjson.JSONObject;
import com.xhh.rbac.common.entity.RbacResponse;
import com.xhh.rbac.common.utils.RbacUtil;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class RbacAuthExceptionEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response,
                         AuthenticationException authException) throws IOException {
        RbacResponse rbacResponse = new RbacResponse();
        RbacUtil.makeResponse(
                response, MediaType.APPLICATION_JSON_UTF8_VALUE,
                HttpServletResponse.SC_UNAUTHORIZED, rbacResponse.message("token无效")
        );
    }
}