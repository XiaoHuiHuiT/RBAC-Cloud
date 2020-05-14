package com.xhh.rbac.common.selector;

import com.xhh.rbac.common.configure.RbacAuthExceptionConfigure;
import com.xhh.rbac.common.configure.RbacOAuth2FeignConfigure;
import com.xhh.rbac.common.configure.RbacServerProtectConfigure;
import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;

public class RbacCloudApplicationSelector implements ImportSelector {

    @Override
    public String[] selectImports(AnnotationMetadata annotationMetadata) {
        return new String[]{
                RbacAuthExceptionConfigure.class.getName(),
                RbacOAuth2FeignConfigure.class.getName(),
                RbacServerProtectConfigure.class.getName()
        };
    }
}