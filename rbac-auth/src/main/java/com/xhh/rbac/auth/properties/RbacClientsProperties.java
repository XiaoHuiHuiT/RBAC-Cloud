package com.xhh.rbac.auth.properties;

import lombok.Data;

@Data
public class RbacClientsProperties {

    // client对应client_id
    private String client;
    // secret对应client_secret
    private String secret;
    // grantType对应当前令牌支持的认证类型
    private String grantType = "password,authorization_code,refresh_token";
    // scope对应认证范围
    private String scope = "all";

    // grantType和scope包含默认值。
}