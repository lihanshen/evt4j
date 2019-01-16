package io.everitoken.sdk.java.keyProvider;

import io.everitoken.sdk.java.exceptions.WifFormatException;

import java.util.Arrays;

public class MultiKeyProvider extends KeyProvider {
    public MultiKeyProvider(String[] key) throws WifFormatException {
        super(Arrays.asList(key));
    }
}
