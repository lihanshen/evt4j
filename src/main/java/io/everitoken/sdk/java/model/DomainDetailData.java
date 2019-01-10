package io.everitoken.sdk.java.model;

import io.everitoken.sdk.java.EvtSdkException;
import io.everitoken.sdk.java.PublicKey;
import org.joda.time.DateTime;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class DomainDetailData extends NameableResource implements Meta, Addressable {
    private JSONArray metas;
    private PublicKey creator;
    private JSONObject transfer;
    private String address;
    private JSONObject issue;
    private JSONObject manage;
    private DateTime createdTime;

    // TODO: protect transfer, issue and manage with interface
    public DomainDetailData(JSONObject raw) throws EvtSdkException {
        super(raw.getString("name"));
        address = raw.getString("address");
        metas = raw.getJSONArray("metas");
        createdTime = new DateTime(raw.getString("create_time"));
        creator = new PublicKey(raw.getString("creator"));
        transfer = raw.getJSONObject("transfer");
        issue = raw.getJSONObject("issue");
        manage = raw.getJSONObject("manage");

    }

    public static void main(String[] args) {
        System.out.println(PublicKey.isValidAddress("EVT0000005ZbVoKRDdy6N4r22sSn3WDyB4YkTcf5R1dSjAsmRnFEF"));
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