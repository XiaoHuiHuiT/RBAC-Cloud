package com.xhh.rbac.server.system.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@Slf4j
@RestController
public class TestController {

    @GetMapping("info")
    public String test() {
        return "rbac-server-system";
    }

    @GetMapping("currentUser")
    public Principal currentUser(Principal principal) {
        return principal;
    }

    // java -jar zipkin.jar --server.port=8402 --zipkin.storage.type=mysql --zipkin.storage.mysql.db=febs_cloud_base --zipkin.storage.mysql.username=root --zipkin.storage.mysql.password=123456 --zipkin.storage.mysql.host=localhost --zipkin.storage.mysql.port=3306 --zipkin.collector.rabbitmq.addresses=localhost:5672 --zipkin.collector.rabbitmq.username=febs --zipkin.collector.rabbitmq.password=123456
    // java -jar zipkin.jar --server.port=8402 --zipkin.storage.type=mysql --zipkin.storage.mysql.db=rbac_cloud_base --zipkin.storage.mysql.username=root --zipkin.storage.mysql.password=1234 --zipkin.storage.mysql.host=localhost --zipkin.storage.mysql.port=3306 --zipkin.collector.rabbitmq.addresses=39.106.71.75:5672 --zipkin.collector.rabbitmq.username=rbac --zipkin.collector.rabbitmq.password=1234
    @GetMapping("hello")
    public String hello(String name) {
        log.info("/hello服务被调用");
        return "hello" + name;
    }
}