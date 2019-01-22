package io.everitoken.sdk.java.dto;

import com.alibaba.fastjson.JSON;

import java.util.List;

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
