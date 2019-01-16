package io.everitoken.sdk.java.params;

import org.json.JSONObject;

public class IdParams implements ApiParams {
    private final int id;

    public IdParams(int id) {
        this.id = id;
    }

    @Override
    public JSONObject asJson() {
        JSONObject payload = new JSONObject();
        payload.put("id", id);

        return payload;
    }
}
