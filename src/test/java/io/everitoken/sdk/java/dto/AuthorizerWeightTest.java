package io.everitoken.sdk.java.dto;

import com.alibaba.fastjson.JSON;

import org.json.JSONObject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import io.everitoken.sdk.java.PublicKey;

class AuthorizerWeightTest {
    @Test
    @DisplayName("JSON serialize")
    void toJSONString() {
        AuthorizerWeight authorizerWeight = AuthorizerWeight.createOwner(2);
        JSONObject json = new JSONObject(JSON.toJSONString(authorizerWeight));
        Assertions.assertEquals("[G] .OWNER", json.getString("ref"));
        Assertions.assertEquals(2, json.getInt("weight"));

        PublicKey key = PublicKey.of("EVT76uLwUD5t6fkob9Rbc9UxHgdTVshNceyv2hmppw4d82j2zYRpa");
        AuthorizerWeight authorizerWeight1 = AuthorizerWeight.createAccount(key, 1);
        JSONObject json1 = new JSONObject(JSON.toJSONString(authorizerWeight1));
        Assertions.assertEquals(json1.getString("ref"),
                String.format("%s %s", "[A]", "EVT76uLwUD5t6fkob9Rbc9UxHgdTVshNceyv2hmppw4d82j2zYRpa"));
        Assertions.assertEquals(1, json1.getInt("weight"));
    }

    @Test
    @DisplayName("JSON parse from string")
    void toObjectFromJson() {
        AuthorizerWeight authorizerWeight = JSON.parseObject("{\"ref\":\"[G] .OWNER\",\"weight\":2}",
                AuthorizerWeight.class);
        Assertions.assertEquals("[G] .OWNER", authorizerWeight.getRef());
        Assertions.assertEquals(2, authorizerWeight.getWeight());
    }
}