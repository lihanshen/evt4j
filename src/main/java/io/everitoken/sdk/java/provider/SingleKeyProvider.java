package io.everitoken.sdk.java.provider;

import java.util.Arrays;

public class SingleKeyProvider extends KeyProvider {
    public SingleKeyProvider(String key) {
        super(Arrays.asList(key));
    }
}
