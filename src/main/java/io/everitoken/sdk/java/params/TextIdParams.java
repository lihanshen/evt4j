package io.everitoken.sdk.java.params;

import org.json.JSONObject;

public class TextIdParams implements ApiParams {
    private final String id;

    public TextIdParams(String id) {
        this.id = id;
    }

    @Override
    public JSONObject asJson() {
        JSONObject payload = new JSONObject();
        payload.put("block_id", id);

        return payload;
    }
}
