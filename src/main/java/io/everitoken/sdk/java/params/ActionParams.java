package io.everitoken.sdk.java.params;

import org.json.JSONObject;

public class ActionParams implements ApiParams, Paginatable {
    private String domain;
    private String key;
    private int skip = 0;
    private int take = 10;

    public ActionParams(String domain, String key, int skip, int take) {
        this.domain = domain;
        this.key = key;
        this.skip = skip;
        this.take = take;
    }

    public ActionParams(String domain) {
        this(domain, null, 0, 10);
    }

    public ActionParams(String domain, String key) {
        this(domain, key, 0, 10);
    }

    public JSONObject asJson() {
        JSONObject payload = new JSONObject();
        payload.put("domain", domain);

        if (key != null) {
            payload.put("key", key);
        }
        payload.put("skip", skip);
        payload.put("take", take);

        return payload;
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
