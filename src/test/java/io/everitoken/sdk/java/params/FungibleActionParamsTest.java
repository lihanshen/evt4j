package io.everitoken.sdk.java.params;

import org.json.JSONObject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class FungibleActionParamsTest {
    @Test
    @DisplayName("Serialization should be correct")
    void asJSONString() {
        FungibleActionParams params = new FungibleActionParams("address", "testSymbol");
        JSONObject json = params.asJson();

        Assertions.assertEquals(json.getString("addr"), "address");
        Assertions.assertEquals(json.getString("sym_id"), "testSymbol");
        Assertions.assertEquals(json.getInt("skip"), 0);
        Assertions.assertEquals(json.getInt("take"), 10);
    }
}