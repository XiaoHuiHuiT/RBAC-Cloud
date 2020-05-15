package com.xhh.rbac.auth.properties;

import lombok.Data;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;

@Data
// @SpringBootConfiguration实质上为@Component的派生注解，用于将RbacAuthProperties纳入到IOC容器中。
@SpringBootConfiguration
// 用于指定读取的配置文件路径
@PropertySource(value = {"classpath:rbac-auth.properties"})
// 指定了要读取的属性的统一前缀名称为rbac.auth；
@ConfigurationProperties(prefix = "rbac.auth")
public class RbacAuthProperties {

    // 因为一个认证服务器可以根据多种Client来发放对应的令牌，所以这个属性使用的是数组形式
    private RbacClientsProperties[] clients = {};
    // accessTokenValiditySeconds用于指定access_token的有效时间，默认值为60 * 60 * 24秒
    private int accessTokenValiditySeconds = 60 * 60 * 24;
    // refreshTokenValiditySeconds用于指定refresh_token的有效时间，默认值为60 * 60 * 24 * 7秒
    private int refreshTokenValiditySeconds = 60 * 60 * 24 * 7;

    // 免认证路径
    private String anonUrl;

    //验证码配置类
    private RbacValidateCodeProperties code = new RbacValidateCodeProperties();
}