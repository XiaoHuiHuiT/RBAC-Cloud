package com.xhh.rbac.common.entity;

import java.util.HashMap;

public class RbacResponse extends HashMap<String, Object> {

    private static final long serialVersionUID = -8713837118340960775L;

    public RbacResponse message(String message) {
        this.put("message", message);
        return this;
    }

    public RbacResponse data(Object data) {
        this.put("data", data);
        return this;
    }

    @Override
    public RbacResponse put(String key, Object value) {
        super.put(key, value);
        return this;
    }

    public String getMessage() {
        return String.valueOf(get("message"));
    }

    public Object getData() {
        return get("data");
    }
}