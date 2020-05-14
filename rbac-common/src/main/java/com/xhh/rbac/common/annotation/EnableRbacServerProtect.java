package com.xhh.rbac.common.annotation;

import com.xhh.rbac.common.configure.RbacServerProtectConfigure;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import(RbacServerProtectConfigure.class)
public @interface EnableRbacServerProtect {

}