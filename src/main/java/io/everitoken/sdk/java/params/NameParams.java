package io.everitoken.sdk.java.params;

import com.alibaba.fastjson.JSON;

public class NameParams implements ApiParams {
    private final String name;

    public NameParams(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public String asBody() {
        return JSON.toJSONString(this);
    }
}
