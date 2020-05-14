package com.xhh.rbacserversystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

// 开启服务注册与发现
@EnableDiscoveryClient
@SpringBootApplication
public class RBACServerSystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(RBACServerSystemApplication.class, args);
    }

}