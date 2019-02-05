package io.everitoken.sdk.java.param;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.annotation.JSONField;

public class FungibleActionParams implements ApiParams, Paginatable {
    private final String address;
    private final String symbolId;
    private final int skip;
    private final int take;

    FungibleActionParams(String address, String symbolId, int skip, int take) {
        this.address = address;
        this.symbolId = symbolId;
        this.skip = skip;
        this.take = take;
    }

    FungibleActionParams(String address, String symbolId) {
        this(address, symbolId, 0, 10);
    }

    @Override
    public String asBody() {
        return JSON.toJSONString(this);
    }

    @Override
    public int getSkip() {
        return skip;
    }

    @Override
    public int getTake() {
        return take;
    }

    @JSONField(name = "addr")
    public String getAddress() {
        return address;
    }

    @JSONField(name = "sym_id")
    public String getSymbolId() {
        return symbolId;
    }
}
