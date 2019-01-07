package io.everitoken.sdk.java.params;

import org.json.JSONObject;

public class TokenDetailParams implements ApiParams {
    private String domain;
    private String name;

    public TokenDetailParams(String domain, String name) {
        this.domain = domain;
        this.name = name;
    }

    public JSONObject asJson() {
        JSONObject payload = new JSONObject();
        payload.put("domain", domain);
        payload.put("name", name);

        return payload;
    }
}
