package com.xhh.rbac.common.annotation;

import com.xhh.rbac.common.configure.RbacOAuth2FeignConfigure;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import(RbacOAuth2FeignConfigure.class)
public @interface EnableRbacOauth2FeignClient {

}