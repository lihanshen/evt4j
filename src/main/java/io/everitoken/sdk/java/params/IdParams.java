package io.everitoken.sdk.java.params;

import com.alibaba.fastjson.JSON;

public class IdParams implements ApiParams {
    private final int id;

    public IdParams(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    @Override
    public String asBody() {
        return JSON.toJSONString(this);
    }
}
