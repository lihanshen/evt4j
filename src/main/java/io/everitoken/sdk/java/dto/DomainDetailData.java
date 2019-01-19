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
    private final JSONObject transfer;
    private final String address;
    private final JSONObject issue;
    private final JSONObject manage;
    private final DateTime createdTime;

    // TODO: protect transfer, issue and manage with interface
    private DomainDetailData(@NotNull JSONObject raw) throws JSONException {
        name = raw.getString("name");
        address = raw.getString("address");
        metas = raw.getJSONArray("metas");
        createdTime = new DateTime(raw.getString("create_time"));
        creator = PublicKey.of(raw.getString("creator"));
        transfer = raw.getJSONObject("transfer");
        issue = raw.getJSONObject("issue");
        manage = raw.getJSONObject("manage");

    }

    @NotNull
    @Contract("_ -> new")
    public static DomainDetailData create(JSONObject raw) throws JSONException {
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

    public JSONObject getTransfer() {
        return transfer;
    }

    @Override
    public String getAddress() {
        return address;
    }

    public JSONObject getIssue() {
        return issue;
    }

    public JSONObject getManage() {
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