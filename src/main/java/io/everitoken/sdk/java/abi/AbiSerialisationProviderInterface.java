package io.everitoken.sdk.java.abi;

@FunctionalInterface
public interface AbiSerialisationProviderInterface {
    String serialize(String data);
}
