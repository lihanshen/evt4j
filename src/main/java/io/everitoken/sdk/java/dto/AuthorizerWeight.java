package io.everitoken.sdk.java.dto;

import com.alibaba.fastjson.JSON;
import io.everitoken.sdk.java.PublicKey;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.json.JSONObject;

import java.util.Objects;

public class AuthorizerWeight {
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
        AuthorizerRef account = AuthorizerRef.createAccount(key);
        return new AuthorizerWeight(String.format(account.toString(), key.toString()), weightType);
    }

    @NotNull
    @Contract("_, _ -> new")
    public static AuthorizerWeight createGroup(@NotNull PublicKey key, int weightType) {
        AuthorizerRef group = AuthorizerRef.createGroup(key);
        return new AuthorizerWeight(group.toString(), weightType);
    }

    @NotNull
    @Contract("_ -> new")
    public static AuthorizerWeight createOwner(int weightType) {
        AuthorizerRef group = AuthorizerRef.createGroup();
        return new AuthorizerWeight(group.toString(), weightType);
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
