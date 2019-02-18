package io.everitoken.sdk.java.abi;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.annotation.JSONField;
import org.json.JSONObject;

public abstract class Abi {
    private final String name;
    private final String key;
    private final String domain;

    Abi(String name, String key, String domain) {
        this.name = name;
        this.key = key;
        this.domain = domain;
    }

    public String serialize(AbiSerialisationProviderInterface provider) {
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

    @JSONField(name = "domain")
    public String getDomain() {
        return domain;
    }

    public String getData(AbiSerialisationProviderInterface provider) {
        // TODO remove print
        System.out.println(JSON.toJSONString(new AbiToBin<>(getName(), this)));
        return provider.serialize(JSON.toJSONString(new AbiToBin<>(getName(), this)));
    }
}
