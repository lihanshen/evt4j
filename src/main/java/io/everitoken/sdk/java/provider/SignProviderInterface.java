package io.everitoken.sdk.java.provider;

import io.everitoken.sdk.java.Signature;

import java.util.List;

public interface SignProviderInterface {
    List<Signature> get(byte[] bufToSign);
}
