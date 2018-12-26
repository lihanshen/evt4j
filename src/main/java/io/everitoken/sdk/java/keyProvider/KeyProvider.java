package io.everitoken.sdk.java.keyProvider;

import io.everitoken.sdk.java.EvtSdkException;
import io.everitoken.sdk.java.PrivateKey;

import java.util.ArrayList;
import java.util.List;

public class KeyProvider {
    private List<PrivateKey> keys = new ArrayList<PrivateKey>();

    public KeyProvider(List<String> keys) throws EvtSdkException {
        for (String key : keys) {
            PrivateKey privateKey = PrivateKey.fromWif(key);
            this.keys.add(privateKey);
        }
    }

    public List<PrivateKey> getKeys() {
        return keys;
    }
}
