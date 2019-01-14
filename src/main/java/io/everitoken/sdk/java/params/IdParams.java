package io.everitoken.sdk.java.params;

import org.json.JSONObject;

public class IdParams implements ApiParams {
    private int id;

    public IdParams(int id) {
        this.id = id;
    }

    public JSONObject asJson() {
        JSONObject payload = new JSONObject();
        payload.put("id", id);

        return payload;
    }
}
