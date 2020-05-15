package com.xhh.rbac.common.validator;

import com.xhh.rbac.common.annotation.IsMobile;
import com.xhh.rbac.common.entity.RegexpConstant;
import com.xhh.rbac.common.utils.RbacUtil;
import org.apache.commons.lang3.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class MobileValidator implements ConstraintValidator<IsMobile, String> {

    @Override
    public void initialize(IsMobile isMobile) {
    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        try {
            if (StringUtils.isBlank(s)) {
                return true;
            } else {
                // RegexpConstant为正则表达式常量
                String regex = RegexpConstant.MOBILE_REG;
                return RbacUtil.match(regex, s);
            }
        } catch (Exception e) {
            return false;
        }
    }
}