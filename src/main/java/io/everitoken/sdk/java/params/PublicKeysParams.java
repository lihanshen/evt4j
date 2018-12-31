package io.everitoken.sdk.java.params;

import io.everitoken.sdk.java.EvtSdkException;
import io.everitoken.sdk.java.PublicKey;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class PublicKeysParams {
    private List<PublicKey> publicKeys = new ArrayList<>();

    public PublicKeysParams(String[] publicKeys) throws EvtSdkException {
        for (String publicKey : publicKeys) {
            this.publicKeys.add(new PublicKey(publicKey));
        }
    }

    public PublicKeysParams(List<PublicKey> publicKeys) {
        this.publicKeys = publicKeys;
    }

    public List<String> getPublicKeyAsStringList() {
        return publicKeys.stream().map(PublicKey::toString).collect(Collectors.toList());
    }
}
