package com.xhh.rbac.gateway.filter;

import com.netflix.zuul.context.RequestContext;
import com.xhh.rbac.common.entity.RbacResponse;
import com.xhh.rbac.common.utils.RbacUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.cloud.netflix.zuul.filters.post.SendErrorFilter;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.util.ReflectionUtils;

import javax.servlet.http.HttpServletResponse;

@Slf4j
@Component
public class RbacGatewayErrorFilter extends SendErrorFilter {

    @Override
    public Object run() {
        try {
            RbacResponse RbacResponse = new RbacResponse();
            RequestContext ctx = RequestContext.getCurrentContext();
            String serviceId = (String) ctx.get(FilterConstants.SERVICE_ID_KEY);

            ExceptionHolder exception = findZuulException(ctx.getThrowable());
            String errorCause = exception.getErrorCause();
            Throwable throwable = exception.getThrowable();
            String message = throwable.getMessage();
            message = StringUtils.isBlank(message) ? errorCause : message;
            RbacResponse = resolveExceptionMessage(message, serviceId, RbacResponse);

            HttpServletResponse response = ctx.getResponse();
            RbacUtil.makeResponse(
                    response, MediaType.APPLICATION_JSON_UTF8_VALUE,
                    HttpServletResponse.SC_INTERNAL_SERVER_ERROR, RbacResponse
            );
            log.error("Zull sendError：{}", RbacResponse.getMessage());
        } catch (Exception ex) {
            log.error("Zuul sendError", ex);
            ReflectionUtils.rethrowRuntimeException(ex);
        }
        return null;
    }

    private RbacResponse resolveExceptionMessage(String message, String serviceId, RbacResponse RbacResponse) {
        if (StringUtils.containsIgnoreCase(message, "time out")) {
            return RbacResponse.message("请求" + serviceId + "服务超时");
        }
        if (StringUtils.containsIgnoreCase(message, "forwarding error")) {
            return RbacResponse.message(serviceId + "服务不可用");
        }
        return RbacResponse.message("Zuul请求" + serviceId + "服务异常");
    }
}
