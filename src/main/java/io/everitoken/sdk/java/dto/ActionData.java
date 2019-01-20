package io.everitoken.sdk.java.dto;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.joda.time.DateTime;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Objects;

public class ActionData implements Transactable {

    private final String trxId;
    private final JSONObject data;
    private final String domain;
    private final String name;
    private final String key;
    private final DateTime timestamp;

    private ActionData(@NotNull JSONObject raw) throws JSONException {
        trxId = raw.getString("trx_id");
        data = raw.getJSONObject("data");
        domain = raw.getString("domain");
        key = raw.getString("key");
        name = raw.getString("name");
        timestamp = new DateTime(raw.getString("timestamp"));
    }

    @NotNull
    @Contract("_ -> new")
    public static ActionData create(JSONObject raw) throws JSONException, NullPointerException {
        Objects.requireNonNull(raw);
        return new ActionData(raw);
    }

    @Override
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
