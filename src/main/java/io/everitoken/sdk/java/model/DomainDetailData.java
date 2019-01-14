package io.everitoken.sdk.java.model;

import io.everitoken.sdk.java.EvtSdkException;
import io.everitoken.sdk.java.PublicKey;
import org.joda.time.DateTime;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Objects;

public class DomainDetailData extends NameableResource implements Meta, Addressable {
    private JSONArray metas;
    private PublicKey creator;
    private JSONObject transfer;
    private String address;
    private JSONObject issue;
    private JSONObject manage;
    private DateTime createdTime;

    // TODO: protect transfer, issue and manage with interface
    private DomainDetailData(JSONObject raw) throws EvtSdkException, JSONException {
        super(raw.getString("name"));
        address = raw.getString("address");
        metas = raw.getJSONArray("metas");
        createdTime = new DateTime(raw.getString("create_time"));
        creator = new PublicKey(raw.getString("creator"));
        transfer = raw.getJSONObject("transfer");
        issue = raw.getJSONObject("issue");
        manage = raw.getJSONObject("manage");

    }

    public static DomainDetailData create(JSONObject raw) throws NullPointerException, JSONException, EvtSdkException {
        Objects.requireNonNull(raw);
        return new DomainDetailData(raw);
    }

    public String getName() {
        return name;
    }

    public JSONArray getMetas() {
        return metas;
    }

    public String getCreator() {
        return creator.toString();
    }

    public JSONObject getTransfer() {
        return transfer;
    }

    public String getAddress() {
        return address.toString();
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

    public String toString() {
        return getName();
    }
}