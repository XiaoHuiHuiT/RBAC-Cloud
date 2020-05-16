package com.xhh.rbac.common.entity.router;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

@Data
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)// 如果属性值是null的话，不参与序列化。
public class RouterMeta implements Serializable {

    private static final long serialVersionUID = 5499925008927195914L;
    /**
     * 标题
     */
    private String title;
    /**
     * 图标
     */
    private String icon;
}