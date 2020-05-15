package com.xhh.rbac.common.annotation;

import com.xhh.rbac.common.validator.MobileValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = MobileValidator.class)// MobileValidator用于编写具体的校验逻辑
public @interface IsMobile {

    String message();

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}