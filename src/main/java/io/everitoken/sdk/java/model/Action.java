package io.everitoken.sdk.java.model;

import org.joda.time.DateTime;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Objects;

public class Action implements Transactable {

    private String trxId;
    private JSONObject data;
    private String domain;
    private String name;
    private String key;
    private DateTime timestamp;

    private Action(JSONObject raw) throws JSONException {
        trxId = raw.getString("trx_id");
        data = raw.getJSONObject("data");
        domain = raw.getString("domain");
        key = raw.getString("key");
        name = raw.getString("name");
        timestamp = new DateTime(raw.getString("timestamp"));
    }

    public static Action create(JSONObject raw) throws JSONException, NullPointerException {
        Objects.requireNonNull(raw);
        return new Action(raw);
    }

    public String getTrxId() {
        return trxId;
    }

    public JSONObject getData() {
        return data;
    }

    public String getDomain() {
        return domain;
    }

    public String getName() {
        return name;
    }

    public String getKey() {
        return key;
    }

    public DateTime getTimestamp() {
        return timestamp;
    }
}
