package io.everitoken.sdk.java.abi;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.annotation.JSONField;
import org.json.JSONObject;

abstract class Abi {
    private final String name;
    private final String key;
    private final String domain;

    Abi(String name, String key, String domain) {
        this.name = name;
        this.key = key;
        this.domain = domain;
    }

    String serialize(AbiSerialisationProvider provider) {
        JSONObject payload = new JSONObject();
        payload.put("name", getName());
        payload.put("key", getKey());
        payload.put("domain", getDomain());
        payload.put("data", getData(provider));
        return payload.toString();
    }

    public String getName() {
        return name;
    }

    public String getKey() {
        return key;
    }

    @JSONField(name = "name")
    public String getDomain() {
        return domain;
    }

    public String getData(AbiSerialisationProvider provider) {
        return provider.serialize(JSON.toJSONString(new AbiToBin<>(getName(), this)));
    }
}
