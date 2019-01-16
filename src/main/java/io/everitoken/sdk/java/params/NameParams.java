package io.everitoken.sdk.java.params;

import org.json.JSONObject;

public class NameParams implements ApiParams {
    private final String name;

    public NameParams(String name) {
        this.name = name;
    }

    @Override
    public JSONObject asJson() {
        JSONObject payload = new JSONObject();
        payload.put("name", name);

        return payload;
    }
}
