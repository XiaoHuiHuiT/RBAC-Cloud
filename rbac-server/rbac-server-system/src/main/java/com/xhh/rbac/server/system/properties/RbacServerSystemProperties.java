package com.xhh.rbac.server.system.properties;

import lombok.Data;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;

@Data
@SpringBootConfiguration
@PropertySource(value = {"classpath:rbac-server-system.properties"})
@ConfigurationProperties(prefix = "rbac.server.system")
public class RbacServerSystemProperties {

    /**
     * 免认证 URI，多个值的话以逗号分隔
     */
    private String anonUrl;

    private RbacSwaggerProperties swagger = new RbacSwaggerProperties();
}