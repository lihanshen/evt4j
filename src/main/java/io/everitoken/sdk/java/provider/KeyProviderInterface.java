package io.everitoken.sdk.java.provider;

import java.util.List;

import io.everitoken.sdk.java.PrivateKey;

@FunctionalInterface
public interface KeyProviderInterface {
    List<PrivateKey> get();
}
