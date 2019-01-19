package io.everitoken.sdk.java.dto;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

class TokenDetailDataTest {

    @Test
    @DisplayName("owners should filter out invalid public keys")
    void getOwners() {
        JSONObject raw = new JSONObject();
        raw.put("name", "testTokenName");
        raw.put("domain", "testDomainName");
        raw.put("metas", new JSONArray(new String[]{}));
        raw.put("owner", new JSONArray(new String[]{"INVALID_EVT_PUBLIC_KEY",
                "EVT76uLwUD5t6fkob9Rbc9UxHgdTVshNceyv2hmppw4d82j2zYRpa"}));

        TokenDetailData tokenDetailData = TokenDetailData.create(raw);

        assertTrue(tokenDetailData.getOwner().size() == 1, "Should filter out invalid public keys");
    }

    @Test
    @DisplayName("Throw exception if valid is not valid")
    void throwExceptionIfJSONIsNotValid() {
        Assertions.assertThrows(JSONException.class, () -> TokenDetailData.create(new JSONObject()));
    }
}