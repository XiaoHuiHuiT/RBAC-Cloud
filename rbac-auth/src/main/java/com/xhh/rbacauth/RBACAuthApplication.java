package com.xhh.rbacauth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class RBACAuthApplication {

    public static void main(String[] args) {
        SpringApplication.run(RBACAuthApplication.class, args);
    }

}
