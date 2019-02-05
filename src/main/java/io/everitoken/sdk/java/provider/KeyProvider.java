package io.everitoken.sdk.java.provider;

import io.everitoken.sdk.java.PrivateKey;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class KeyProvider {
    private final List<PrivateKey> keys = new ArrayList<>();

    public KeyProvider(@NotNull List<String> keys) {
        for (String key : keys) {
            PrivateKey privateKey = PrivateKey.fromWif(key);
            this.keys.add(privateKey);
        }
    }

    public List<PrivateKey> getKeys() {
        return keys;
    }
}
