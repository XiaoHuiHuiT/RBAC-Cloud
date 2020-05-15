package com.xhh.rbac.server.system;

import com.xhh.rbac.common.annotation.EnableRbacAuthExceptionHandler;
import com.xhh.rbac.common.annotation.EnableRbacServerProtect;
import com.xhh.rbac.common.annotation.RbacCloudApplication;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.transaction.annotation.EnableTransactionManagement;

// 开启服务注册与发现
@EnableDiscoveryClient
@SpringBootApplication
// 开启Spring Cloud Security权限注解
@EnableGlobalMethodSecurity(prePostEnabled = true)
@RbacCloudApplication
@MapperScan("com.xhh.rbac.server.system.mapper")
@EnableTransactionManagement
public class RBACServerSystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(RBACServerSystemApplication.class, args);
    }

}