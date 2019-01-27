package io.everitoken.sdk.java.dto;

import com.alibaba.fastjson.JSON;
import io.everitoken.sdk.java.PublicKey;
import org.json.JSONObject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class AuthorizerWeightTest {
    @Test
    @DisplayName("JSON serialize")
    void toJSONString() {
        AuthorizerWeight authorizerWeight = AuthorizerWeight.createOwner(2);
        JSONObject json = new JSONObject(JSON.toJSONString(authorizerWeight));
        Assertions.assertEquals(json.getString("ref"), "[G] .OWNER");
        Assertions.assertEquals(json.getInt("weight"), 2);

        PublicKey key = PublicKey.of("EVT76uLwUD5t6fkob9Rbc9UxHgdTVshNceyv2hmppw4d82j2zYRpa");
        AuthorizerWeight authorizerWeight1 = AuthorizerWeight.createAccount(key, 1);
        JSONObject json1 = new JSONObject(JSON.toJSONString(authorizerWeight1));
        Assertions.assertEquals(
                json1.getString("ref"),
                String.format("%s %s", "[A]", "EVT76uLwUD5t6fkob9Rbc9UxHgdTVshNceyv2hmppw4d82j2zYRpa")
        );
        Assertions.assertEquals(json1.getInt("weight"), 1);
    }

    @Test
    @DisplayName("JSON parse from string")
    void toObjectFromJson() {
        AuthorizerWeight authorizerWeight = JSON.parseObject(
                "{\"ref\":\"[G] .OWNER\",\"weight\":2}",
                AuthorizerWeight.class
        );
        Assertions.assertEquals(authorizerWeight.getRef(), "[G] .OWNER");
        Assertions.assertEquals(authorizerWeight.getWeight(), 2);
    }
}