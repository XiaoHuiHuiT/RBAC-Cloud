package com.xhh.rbac.auth;

import com.xhh.rbac.common.annotation.EnableRbacAuthExceptionHandler;
import com.xhh.rbac.common.annotation.EnableRbacServerProtect;
import com.xhh.rbac.common.annotation.RbacCloudApplication;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
@RbacCloudApplication
public class RBACAuthApplication {

    public static void main(String[] args) {
        SpringApplication.run(RBACAuthApplication.class, args);
    }

}
