package io.everitoken.sdk.java.model;

import io.everitoken.sdk.java.EvtSdkException;
import io.everitoken.sdk.java.PublicKey;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class GroupDetailData extends NameableResource implements Meta {
    private PublicKey key;
    private JSONObject root;
    private JSONArray metas;

    // TODO implement root tree structure, extract to a separate model for node
    public GroupDetailData(JSONObject raw) throws EvtSdkException {
        super(raw.getString("name"));
        key = new PublicKey(raw.getString("key"));
        metas = raw.getJSONArray("metas");
        root = raw.getJSONObject("root");
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