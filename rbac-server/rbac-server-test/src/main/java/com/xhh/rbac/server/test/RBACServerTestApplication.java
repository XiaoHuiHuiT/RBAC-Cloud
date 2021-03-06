package com.xhh.rbac.server.test;

import com.xhh.rbac.common.annotation.EnableRbacAuthExceptionHandler;
import com.xhh.rbac.common.annotation.EnableRbacOauth2FeignClient;
import com.xhh.rbac.common.annotation.EnableRbacServerProtect;
import com.xhh.rbac.common.annotation.RbacCloudApplication;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;

// 开启服务注册与发现
@EnableDiscoveryClient
@SpringBootApplication
// 开启Spring Cloud Security权限注解
@EnableGlobalMethodSecurity(prePostEnabled = true)
@EnableFeignClients// 开启Feign Client功能
@RbacCloudApplication
public class RBACServerTestApplication {

    public static void main(String[] args) {
        SpringApplication.run(RBACServerTestApplication.class, args);
    }

}
