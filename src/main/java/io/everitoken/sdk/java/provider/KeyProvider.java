package io.everitoken.sdk.java.provider;

import io.everitoken.sdk.java.PrivateKey;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.stream.Collectors;

public class KeyProvider implements KeyProviderInterface {
    private final List<PrivateKey> keys;

    public KeyProvider(@NotNull final List<String> keys) {
        this.keys = keys.stream().map(PrivateKey::fromWif).collect(Collectors.toList());
    }

    public List<PrivateKey> get() {
        return keys;
    }
}
