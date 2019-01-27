package io.everitoken.sdk.java.dto;

import com.alibaba.fastjson.JSON;
import io.everitoken.sdk.java.PublicKey;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.json.JSONObject;

import java.util.Objects;

public class AuthorizerWeight {
    private static final String ACCOUNT_IDENTIFIER = "[A]";
    private static final String GROUP_IDENTIFIER = "[G]";
    private final String ref;
    private final int weight;

    public AuthorizerWeight(String ref, int weight) {
        this.ref = ref;
        this.weight = weight;
    }

    @NotNull
    @Contract("_ -> new")
    public static AuthorizerWeight ofRaw(@NotNull JSONObject raw) {
        Objects.requireNonNull(raw);
        return new AuthorizerWeight(raw.getString("ref"), raw.getInt("weight"));
    }

    @NotNull
    @Contract("_, _ -> new")
    public static AuthorizerWeight createAccount(@NotNull PublicKey key, int weightType) {
        return new AuthorizerWeight(String.format("%s %s", ACCOUNT_IDENTIFIER, key.toString()), weightType);
    }

    @NotNull
    @Contract("_, _ -> new")
    public static AuthorizerWeight createGroup(@NotNull PublicKey key, int weightType) {
        return new AuthorizerWeight(String.format("%s %s", GROUP_IDENTIFIER, key.toString()), weightType);
    }

    @NotNull
    @Contract("_ -> new")
    public static AuthorizerWeight createOwner(int weightType) {
        return new AuthorizerWeight(String.format("%s %s", GROUP_IDENTIFIER, ".OWNER"), weightType);
    }

    public String getRef() {
        return ref;
    }

    public int getWeight() {
        return weight;
    }

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }
}
