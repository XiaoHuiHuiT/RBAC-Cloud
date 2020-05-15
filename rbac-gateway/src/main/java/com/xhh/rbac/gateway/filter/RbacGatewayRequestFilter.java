package com.xhh.rbac.gateway.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.xhh.rbac.common.entity.RbacConstant;
import com.xhh.rbac.common.entity.RbacResponse;
import com.xhh.rbac.common.utils.RbacUtil;
import com.xhh.rbac.gateway.properties.RbacGatewayProperties;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.Base64Utils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/*
filterType，对应Zuul生命周期的四个阶段：pre、post、route和error，我们要在请求转发出去前添加请求头，所以这里指定为pre；
filterOrder，过滤器的优先级，数字越小，优先级越高。PreDecorationFilter过滤器的优先级为5，所以我们可以指定为6让我们的过滤器优先级比它低；
shouldFilter，方法返回boolean类型，true时表示是否执行该过滤器的run方法，false则表示不执行；
run，定义过滤器的主要逻辑。这里我们通过请求上下文RequestContext获取了转发的服务名称serviceId和请求对象HttpServletRequest，并打印请求日志。随后往请求上下文的头部添加了Key为ZuulToken，Value为rbac:zuul:1234的信息。这两个值可以抽取到常量类中。
*/
@Slf4j
@Component
public class RbacGatewayRequestFilter extends ZuulFilter {

    @Autowired
    private RbacGatewayProperties properties;

    private AntPathMatcher pathMatcher = new AntPathMatcher();

    @Override
    public String filterType() {
        return FilterConstants.PRE_TYPE;
    }

    @Override
    public int filterOrder() {
        return 6;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() {
        RequestContext ctx = RequestContext.getCurrentContext();
        String serviceId = (String) ctx.get(FilterConstants.SERVICE_ID_KEY);
        HttpServletRequest request = ctx.getRequest();
        String host = request.getRemoteHost();
        String method = request.getMethod();
        String uri = request.getRequestURI();
        log.info("请求URI：{}，HTTP Method：{}，请求IP：{}，ServerId：{}", uri, method, host, serviceId);

        // 禁止外部访问资源实现
        boolean shouldForward = true;
        String forbidRequestUri = properties.getForbidRequestUri();
        String[] forbidRequestUris = StringUtils.splitByWholeSeparatorPreserveAllTokens(forbidRequestUri, ",");
        if (forbidRequestUris != null && ArrayUtils.isNotEmpty(forbidRequestUris)) {
            for (String u : forbidRequestUris) {
                if (pathMatcher.match(u, uri)) {
                    shouldForward = false;
                }
            }
        }
        if (!shouldForward) {
            HttpServletResponse response = ctx.getResponse();
            RbacResponse RbacResponse = new RbacResponse().message("该URI不允许外部访问");
            try {

                RbacUtil.makeResponse(
                        response, MediaType.APPLICATION_JSON_UTF8_VALUE,
                        HttpServletResponse.SC_FORBIDDEN, RbacResponse
                );
                ctx.setSendZuulResponse(false);
                ctx.setResponse(response);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        byte[] token = Base64Utils.encode((RbacConstant.ZUUL_TOKEN_VALUE).getBytes());
        ctx.addZuulRequestHeader(RbacConstant.ZUUL_TOKEN_HEADER, new String(token));
        return null;
    }
}