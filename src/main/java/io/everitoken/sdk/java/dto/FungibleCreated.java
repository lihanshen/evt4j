package io.everitoken.sdk.java.dto;

import java.util.List;

import com.alibaba.fastjson.JSON;

public class FungibleCreated {
    private final List<Integer> ids;

    public FungibleCreated(List<Integer> ids) {
        this.ids = ids;
    }

    public List<Integer> getIds() {
        return ids;
    }

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }
}
