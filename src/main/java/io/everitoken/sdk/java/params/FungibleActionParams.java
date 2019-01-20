package io.everitoken.sdk.java.params;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.annotation.JSONField;
import org.json.JSONObject;

public class FungibleActionParams implements ApiParams, Paginatable {
    private final String address;
    private final String symbolId;
    private final int skip;
    private final int take;

    public FungibleActionParams(String address, String symbolId, int skip, int take) {
        this.address = address;
        this.symbolId = symbolId;
        this.skip = skip;
        this.take = take;
    }

    public FungibleActionParams(String address, String symbolId) {
        this(address, symbolId, 0, 10);
    }

    @Override
    public JSONObject asJson() {
        return new JSONObject(JSON.toJSONString(this));
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
