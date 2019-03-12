package io.everitoken.sdk.java.provider;

import io.everitoken.sdk.java.PrivateKey;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class KeyProvider implements KeyProviderInterface {
    private final List<PrivateKey> keys;

    private KeyProvider(@NotNull final List<String> keys) {
        this.keys = keys.stream().map(PrivateKey::of).collect(Collectors.toList());
    }

    public static KeyProvider of(String key) {
        return new KeyProvider(Collections.singletonList(key));
    }

    public static KeyProvider of(String[] keys) {
        return new KeyProvider(Arrays.asList(keys));
    }


    public List<PrivateKey> get() {
        return keys;
    }
}
