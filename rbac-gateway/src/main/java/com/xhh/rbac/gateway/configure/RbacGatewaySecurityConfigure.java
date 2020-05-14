package com.xhh.rbac.gateway.configure;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * 　　* @描述: 这里主要是关闭了csrf功能，否则会报csrf相关异常。
 * 　　* @参数 ${tags}
 * 　　* @返回值 ${return_type}
 * 　　* @throws
 * 　　* @作者: 小灰灰
 * 　　* @时间 2020-05-14 16:29
 *
 */
@EnableWebSecurity
public class RbacGatewaySecurityConfigure extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
    }
}