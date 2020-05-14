package com.xhh.rbac.common.annotation;

import com.xhh.rbac.common.selector.RbacCloudApplicationSelector;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import(RbacCloudApplicationSelector.class)
public @interface RbacCloudApplication {

}