package io.everitoken.sdk.java.params;

import org.json.JSONObject;

public class TokenDetailParams implements ApiParams {
    private final String domain;
    private final String name;

    public TokenDetailParams(String domain, String name) {
        this.domain = domain;
        this.name = name;
    }

    @Override
    public JSONObject asJson() {
        JSONObject payload = new JSONObject();
        payload.put("domain", domain);
        payload.put("name", name);

        return payload;
    }
}
