package io.everitoken.sdk.java.params;

import org.json.JSONObject;

public class FungibleActionParams implements ApiParams, Paginatable {
    private String address;
    private String symbolId;
    private int skip;
    private int take;

    public FungibleActionParams(String address, String symbolId, int skip, int take) {
        this.address = address;
        this.symbolId = symbolId;
        this.skip = skip;
        this.take = take;
    }

    public FungibleActionParams(String address, String symbolId) {
        this(address, symbolId, 0, 10);
    }

    public JSONObject asJson() {
        JSONObject jsonObject = new JSONObject();

        jsonObject.put("addr", address);
        jsonObject.put("sym_id", symbolId);
        jsonObject.put("skip", skip);
        jsonObject.put("take", take);

        return jsonObject;
    }

    @Override
    public int getSkip() {
        return skip;
    }

    @Override
    public int getTake() {
        return take;
    }
}
