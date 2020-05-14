package com.xhh.rbac.common.configure;

import com.xhh.rbac.common.handler.RbacAccessDeniedHandler;
import com.xhh.rbac.common.handler.RbacAuthExceptionEntryPoint;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;

public class RbacAuthExceptionConfigure {

    // @ConditionalOnMissingBean注解的意思是，当IOC容器中没有指定名称或类型的Bean的时候，就注册它。
    @Bean
    @ConditionalOnMissingBean(name = "accessDeniedHandler")
    public RbacAccessDeniedHandler accessDeniedHandler() {
        return new RbacAccessDeniedHandler();
    }

    @Bean
    @ConditionalOnMissingBean(name = "authenticationEntryPoint")
    public RbacAuthExceptionEntryPoint authenticationEntryPoint() {
        return new RbacAuthExceptionEntryPoint();
    }
}