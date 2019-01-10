package io.everitoken.sdk.java.params;

import org.json.JSONObject;

public class NameParams implements ApiParams {
    private String name;

    public NameParams(String name) {
        this.name = name;
    }

    public JSONObject asJson() {
        JSONObject payload = new JSONObject();
        payload.put("name", name);

        return payload;
    }
}
