package io.everitoken.sdk.java.model;

import io.everitoken.sdk.java.PublicKey;
import io.everitoken.sdk.java.exceptions.InvalidPublicKeyException;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Objects;

public class GroupDetailData implements Meta, Namable {
    private String name;
    private PublicKey key;
    private JSONObject root;
    private JSONArray metas;

    // TODO implement root tree structure, extract to a separate model for node
    private GroupDetailData(JSONObject raw) throws JSONException {
        name = raw.getString("name");
        key = new PublicKey(raw.getString("key"));
        metas = raw.getJSONArray("metas");
        root = raw.getJSONObject("root");
    }

    public static GroupDetailData create(JSONObject raw) throws InvalidPublicKeyException {
        Objects.requireNonNull(raw);
        return new GroupDetailData(raw);
    }

    public String getName() {
        return name;
    }

    public String getKey() {
        return key.toString();
    }

    public JSONObject getRoot() {
        return root;
    }

    public JSONArray getMetas() {
        return metas;
    }

    public String toString() {
        return name;
    }
}