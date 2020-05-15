package com.xhh.rbac.auth.configure;

import com.xhh.rbac.auth.filter.ValidateCodeFilter;
import com.xhh.rbac.auth.service.RbacUserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Order(2)
@EnableWebSecurity
public class RbacSecurityConfigure extends WebSecurityConfigurerAdapter {

    @Autowired
    private RbacUserDetailService userDetailService;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private ValidateCodeFilter validateCodeFilter;

    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

     /**
     　　* @描述: 在configure(HttpSecurity http)方法中，通过http.addFilterBefore(validateCodeFilter, UsernamePasswordAuthenticationFilter.class)代码，将ValidateCodeFilter过滤器添加到了UsernamePasswordAuthenticationFilter过滤器前。
     　　* @参数 ${tags}
     　　* @返回值 ${return_type}
     　　* @throws
     　　* @作者: 小灰灰
     　　* @时间 2020-05-15 9:58
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.addFilterBefore(validateCodeFilter, UsernamePasswordAuthenticationFilter.class)
                .requestMatchers()
                .antMatchers("/oauth/**")
                .and()
                .authorizeRequests()
                .antMatchers("/oauth/**").authenticated()
                .and()
                .csrf().disable();
    }


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailService).passwordEncoder(passwordEncoder);
    }


}