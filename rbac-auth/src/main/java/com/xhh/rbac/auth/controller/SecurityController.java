package com.xhh.rbac.auth.controller;

import com.xhh.rbac.common.entity.RbacResponse;
import com.xhh.rbac.common.exception.RbacAuthException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.provider.token.ConsumerTokenServices;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;

@RestController
public class SecurityController {

    @Autowired
    private ConsumerTokenServices consumerTokenServices;

    @GetMapping("oauth/test")
    public String testOauth() {
        return "oauth";
    }

    @GetMapping("user")
    public Principal currentUser(Principal principal) {
        return principal;
    }

    @DeleteMapping("signout")
    public RbacResponse signout(HttpServletRequest request) throws RbacAuthException {
        String authorization = request.getHeader("Authorization");
        String token = StringUtils.replace(authorization, "bearer ", "");
        RbacResponse febsResponse = new RbacResponse();
        if (!consumerTokenServices.revokeToken(token)) {
            throw new RbacAuthException("退出登录失败");
        }
        return new RbacResponse().message("退出登录成功");
    }
}