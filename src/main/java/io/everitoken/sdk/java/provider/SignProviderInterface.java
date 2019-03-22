package io.everitoken.sdk.java.provider;

import java.util.List;

import io.everitoken.sdk.java.Signature;

@FunctionalInterface
public interface SignProviderInterface {
    List<Signature> sign(byte[] bufToSign);
}
