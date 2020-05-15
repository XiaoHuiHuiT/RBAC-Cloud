package com.xhh.rbac.auth;

import com.xhh.rbac.common.annotation.EnableRbacAuthExceptionHandler;
import com.xhh.rbac.common.annotation.EnableRbacLettuceRedis;
import com.xhh.rbac.common.annotation.EnableRbacServerProtect;
import com.xhh.rbac.common.annotation.RbacCloudApplication;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
@RbacCloudApplication
@EnableRbacLettuceRedis
@MapperScan("com.xhh.rbac.auth.mapper")// 作用为将路径下的Mapper类都注册到IOC容器中。
public class RBACAuthApplication {

    public static void main(String[] args) {
        SpringApplication.run(RBACAuthApplication.class, args);
    }

}
