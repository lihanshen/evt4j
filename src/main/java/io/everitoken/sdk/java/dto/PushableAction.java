package io.everitoken.sdk.java.dto;

import io.everitoken.sdk.java.abi.AbiSerialisationProvider;

public interface PushableAction {
    String getKey();

    String getName();

    String getDomain();

    String getData(AbiSerialisationProvider provider);
}