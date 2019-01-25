package io.everitoken.sdk.java.dto;

import org.json.JSONObject;

public class Utils {
    public static JSONObject buildDomainKeyJson(String domain, String key) {
        JSONObject json = new JSONObject();
        json.put("domain", domain);
        json.put("key", key);

        return json;
    }
}
