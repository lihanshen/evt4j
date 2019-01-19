package io.everitoken.sdk.java.dto;

import io.everitoken.sdk.java.PublicKey;
import io.everitoken.sdk.java.exceptions.InvalidPublicKeyException;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Objects;

public class GroupDetailData implements Meta, Namable {
    private final String name;
    private final PublicKey key;
    private final JSONObject root;
    private final JSONArray metas;

    // TODO implement root tree structure, extract to a separate dto for node
    private GroupDetailData(@NotNull JSONObject raw) throws JSONException {
        name = raw.getString("name");
        key = PublicKey.of(raw.getString("key"));
        metas = raw.getJSONArray("metas");
        root = raw.getJSONObject("root");
    }

    @NotNull
    @Contract("_ -> new")
    public static GroupDetailData create(JSONObject raw) throws InvalidPublicKeyException {
        Objects.requireNonNull(raw);
        return new GroupDetailData(raw);
    }

    @Override
    public String getName() {
        return name;
    }

    public String getKey() {
        return key.toString();
    }

    public JSONObject getRoot() {
        return root;
    }

    @Override
    public JSONArray getMetas() {
        return metas;
    }

    @Override
    public String toString() {
        return name;
    }
}