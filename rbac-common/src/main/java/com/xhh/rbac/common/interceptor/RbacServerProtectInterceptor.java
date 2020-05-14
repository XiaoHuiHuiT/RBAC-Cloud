package com.xhh.rbac.common.interceptor;

import com.alibaba.fastjson.JSONObject;
import com.xhh.rbac.common.entity.RbacConstant;
import com.xhh.rbac.common.entity.RbacResponse;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.MediaType;
import org.springframework.util.Base64Utils;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/*
该拦截器可以拦截所有Web请求。在preHandle方法中，我们通过HttpServletRequest获取请求头中的Zuul Token，并校验其正确性，当校验不通过的时候返回403错误。
* */
public class RbacServerProtectInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws IOException {
        // 从请求头中获取 Zuul Token
        String token = request.getHeader(RbacConstant.ZUUL_TOKEN_HEADER);
        String zuulToken = new String(Base64Utils.encode(RbacConstant.ZUUL_TOKEN_VALUE.getBytes()));
        // 校验 Zuul Token的正确性
        if (StringUtils.equals(zuulToken, token)) {
            return true;
        } else {
            RbacResponse rbacResponse = new RbacResponse();
            response.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
            response.getWriter().write(JSONObject.toJSONString(rbacResponse.message("请通过网关获取资源")));
            return false;
        }
    }
}