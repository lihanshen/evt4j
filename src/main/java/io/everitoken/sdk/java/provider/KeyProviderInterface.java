package io.everitoken.sdk.java.provider;

import io.everitoken.sdk.java.PrivateKey;

import java.util.List;

@FunctionalInterface
public interface KeyProviderInterface {
    List<PrivateKey> get();
}
