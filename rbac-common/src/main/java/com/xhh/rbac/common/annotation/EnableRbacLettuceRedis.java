package com.xhh.rbac.common.annotation;

import com.xhh.rbac.common.configure.RbacLettuceRedisConfigure;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import(RbacLettuceRedisConfigure.class)
public @interface EnableRbacLettuceRedis {

}