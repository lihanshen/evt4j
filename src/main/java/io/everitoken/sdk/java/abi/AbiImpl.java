package io.everitoken.sdk.java.abi;

public interface AbiImpl {
    String serialize(AbiSerialisationProvider provider);

    String getKey();

    String getName();

    String getDomain();

    String getData(AbiSerialisationProvider provider);
}
