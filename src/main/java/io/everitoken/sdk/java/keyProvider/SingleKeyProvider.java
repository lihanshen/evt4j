package io.everitoken.sdk.java.keyProvider;

import io.everitoken.sdk.java.EvtSdkException;

import java.util.Arrays;

public class SingleKeyProvider extends KeyProvider {
    public SingleKeyProvider(String key) throws EvtSdkException {
        super(Arrays.asList(key));
    }
}
