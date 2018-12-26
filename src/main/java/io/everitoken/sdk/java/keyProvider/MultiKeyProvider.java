package io.everitoken.sdk.java.keyProvider;

import io.everitoken.sdk.java.EvtSdkException;

import java.util.Arrays;

public class MultiKeyProvider extends KeyProvider {
    public MultiKeyProvider(String[] key) throws EvtSdkException {
        super(Arrays.asList(key));
    }
}
