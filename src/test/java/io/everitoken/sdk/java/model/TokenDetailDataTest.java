package io.everitoken.sdk.java.model;

import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class TokenDetailDataTest {

    @Test
    public void getOwners() {
        JSONObject raw = new JSONObject();
        raw.put("name", "testTokenName");
        raw.put("domain", "testDomainName");
        raw.put("metas", new JSONArray(new String[]{}));
        raw.put("owner", new JSONArray(new String[]{"INVALID_EVT_PUBLIC_KEY",
                "EVT76uLwUD5t6fkob9Rbc9UxHgdTVshNceyv2hmppw4d82j2zYRpa"}));

        TokenDetailData tokenDetailData = new TokenDetailData(raw);

        assertTrue(tokenDetailData.getOwner().size() == 1, "Should filter out invalid public keys");
    }
}