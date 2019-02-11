package io.everitoken.sdk.java.provider;

import io.everitoken.sdk.java.Signature;

import java.util.List;

@FunctionalInterface
public interface SignProviderInterface {
    List<Signature> sign(byte[] bufToSign);
}
