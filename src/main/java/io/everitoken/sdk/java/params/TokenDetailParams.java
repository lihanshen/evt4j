package io.everitoken.sdk.java.params;

import com.alibaba.fastjson.JSON;

public class TokenDetailParams implements ApiParams {
    private final String domain;
    private final String name;

    TokenDetailParams(String domain, String name) {
        this.domain = domain;
        this.name = name;
    }

    public String getDomain() {
        return domain;
    }

    public String getName() {
        return name;
    }

    @Override
    public String asBody() {
        return JSON.toJSONString(this);
    }
}
