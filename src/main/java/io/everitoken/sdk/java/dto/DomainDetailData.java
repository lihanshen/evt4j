package io.everitoken.sdk.java.dto;

import io.everitoken.sdk.java.PublicKey;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.joda.time.DateTime;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Objects;

public class DomainDetailData implements Meta, Addressable, Namable {
    private final String name;
    private final JSONArray metas;
    private final PublicKey creator;
    private final String address;
    private final Permission issue;
    private final Permission transfer;
    private final Permission manage;
    private final DateTime createdTime;

    private DomainDetailData(@NotNull JSONObject raw) {
        name = raw.getString("name");
        address = raw.getString("address");
        metas = raw.getJSONArray("metas");
        createdTime = new DateTime(raw.getString("create_time"));
        creator = PublicKey.of(raw.getString("creator"));
        issue = Permission.ofRaw(raw.getJSONObject("issue"));
        transfer = Permission.ofRaw(raw.getJSONObject("transfer"));
        manage = Permission.ofRaw(raw.getJSONObject("manage"));

    }

    @NotNull
    @Contract("_ -> new")
    public static DomainDetailData of(JSONObject raw) throws JSONException {
        Objects.requireNonNull(raw);
        return new DomainDetailData(raw);
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public JSONArray getMetas() {
        return metas;
    }

    public String getCreator() {
        return creator.toString();
    }

    public Permission getTransfer() {
        return transfer;
    }

    @Override
    public String getAddress() {
        return address;
    }

    public Permission getIssue() {
        return issue;
    }

    public Permission getManage() {
        return manage;
    }

    public DateTime getCreatedTime() {
        return createdTime;
    }

    @Override
    public String toString() {
        return getName();
    }
}