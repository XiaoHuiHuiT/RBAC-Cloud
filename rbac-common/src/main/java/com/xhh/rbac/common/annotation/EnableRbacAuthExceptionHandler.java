package com.xhh.rbac.common.annotation;

import com.xhh.rbac.common.configure.RbacAuthExceptionConfigure;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

// 驱动该配置类。
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
// @Import将RbacAuthExceptionConfigure配置类引入了进来
@Import(RbacAuthExceptionConfigure.class)
public @interface EnableRbacAuthExceptionHandler {

}