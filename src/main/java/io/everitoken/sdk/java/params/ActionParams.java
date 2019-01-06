package io.everitoken.sdk.java.params;

import org.json.JSONArray;
import org.json.JSONObject;

public class ActionParams implements ApiParams, Paginatable {
    private String domain;
    private String key;
    private String[] names = {};
    private int skip = 0;
    private int take = 10;

    public ActionParams(String domain, String key, String[] names, int skip, int take) {
        this.domain = domain;
        this.key = key;
        this.names = names;
        this.skip = skip;
        this.take = take;
    }

    public ActionParams(String domain) {
        this(domain, null, new String[]{}, 0, 10);
    }

    public ActionParams(String domain, String key) {
        this(domain, key, new String[]{}, 0, 10);
    }

    public ActionParams(String domain, String key, String[] names) {
        this(domain, key, names, 0, 10);
    }

    public JSONObject asJson() {
        JSONObject payload = new JSONObject();
        payload.put("domain", domain);

        if (key != null) {
            payload.put("key", key);
        }

        if (names.length != 0) {
            payload.put("names", new JSONArray(names));
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
