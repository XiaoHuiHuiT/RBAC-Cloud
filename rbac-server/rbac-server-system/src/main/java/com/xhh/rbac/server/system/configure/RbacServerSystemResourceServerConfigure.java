package com.xhh.rbac.server.system.configure;

import com.xhh.rbac.common.handler.RbacAccessDeniedHandler;
import com.xhh.rbac.common.handler.RbacAuthExceptionEntryPoint;
import com.xhh.rbac.server.system.properties.RbacServerSystemProperties;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;

/**
 * 上述配置表示所有访问rbac-server-system的请求都需要认证，只有通过认证服务器发放的令牌才能进行访问。
 * 然后在rbac-server-system的入口类RBACServerSystemApplication上
 * 添加@EnableGlobalMethodSecurity(prePostEnabled = true)注解，
 * 表示开启Spring Cloud Security权限注解
 */
@Configuration
@EnableResourceServer
public class RbacServerSystemResourceServerConfigure extends ResourceServerConfigurerAdapter {

    @Autowired
    private RbacAccessDeniedHandler accessDeniedHandler;
    @Autowired
    private RbacAuthExceptionEntryPoint exceptionEntryPoint;

    @Autowired
    private RbacServerSystemProperties properties;

    @Override
    public void configure(HttpSecurity http) throws Exception {
        String[] anonUrls = StringUtils.splitByWholeSeparatorPreserveAllTokens(properties.getAnonUrl(), ",");

        http.csrf().disable()
                .requestMatchers().antMatchers("/**")
                .and()
                .authorizeRequests()
                .antMatchers(anonUrls).permitAll()
                .antMatchers("/**").authenticated();
    }

    @Override
    public void configure(ResourceServerSecurityConfigurer resources) {
        resources.authenticationEntryPoint(exceptionEntryPoint)
                .accessDeniedHandler(accessDeniedHandler);
    }
}