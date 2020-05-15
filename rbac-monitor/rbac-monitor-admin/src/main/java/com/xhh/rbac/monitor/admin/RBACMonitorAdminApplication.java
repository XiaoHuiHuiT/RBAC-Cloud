package com.xhh.rbac.monitor.admin;

import de.codecentric.boot.admin.server.config.EnableAdminServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableAdminServer// 开启Spring Boot Admin服务端功能
@SpringBootApplication
public class RBACMonitorAdminApplication {

    public static void main(String[] args) {
        SpringApplication.run(RBACMonitorAdminApplication.class, args);
    }

}
