package io.everitoken.sdk.java.keyProvider;

import java.util.Arrays;

public class SingleKeyProvider extends KeyProvider {
    public SingleKeyProvider(String key) {
        super(Arrays.asList(key));
    }
}
