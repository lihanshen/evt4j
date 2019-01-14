package io.everitoken.sdk.java.model;

import io.everitoken.sdk.java.EvtSdkException;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class GroupDetailDataTest {

    @Test
    @DisplayName("Throw JSON exception if can't parse json")
    public void throwJSONException() {
        Assertions.assertThrows(JSONException.class, () -> {
            GroupDetailData.create(new JSONObject());
        });
    }

    @Test
    @DisplayName("Throw when public key is invalid")
    public void throwPublicKeyException() {
        Assertions.assertThrows(EvtSdkException.class, () -> {
            JSONObject json = new JSONObject();
            json.put("name", "testName");
            json.put("key", "wrongkey");
            GroupDetailData.create(json);
        });
    }

    @Test
    @DisplayName("Don't throw when everything is fine")
    public void notThrowWhenPayloadIsGood() {
        Assertions.assertDoesNotThrow(() -> {
            JSONObject json = new JSONObject();
            json.put("name", "testName");
            json.put("key", "EVT76uLwUD5t6fkob9Rbc9UxHgdTVshNceyv2hmppw4d82j2zYRpa");
            json.put("metas", new JSONArray());
            json.put("root", new JSONObject());
            GroupDetailData.create(json);
        });
    }
}