package io.everitoken.sdk.java.dto;

import com.alibaba.fastjson.JSON;
import org.json.JSONObject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PermissionTest {
    private static final String raw = "{\"name\":\"issue\",\"threshold\":1,\"authorizers\":[{\"ref\":\"[G] " +
            ".everiToken\"," +
            "\"weight\":1}]}";

    @Test
    @DisplayName("Deserialize successful")
    void deserialize() {
        Assertions.assertDoesNotThrow(() -> {
            Permission permission = Permission.ofRaw(new JSONObject(raw));
            Assertions.assertEquals(JSON.toJSONString(permission), raw);
            Assertions.assertEquals(permission.getName(), "issue");
            Assertions.assertEquals(permission.getThreshold(), 1);
            Assertions.assertEquals(permission.getAuthorizers().get(0).getWeightType(), 1);
        });
    }
}