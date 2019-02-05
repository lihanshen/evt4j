package io.everitoken.sdk.java.provider;

import java.util.Arrays;

public class MultiKeyProvider extends KeyProvider {
    public MultiKeyProvider(String[] key) {
        super(Arrays.asList(key));
    }
}
