package io.everitoken.sdk.java.dto;

import com.alibaba.fastjson.JSON;

public class TokenDomain {
    private final String name;
    private final String domain;

    public TokenDomain(String name, String domain) {
        this.name = name;
        this.domain = domain;
    }

    public String getName() {
        return name;
    }

    public String getDomain() {
        return domain;
    }

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }
}