package io.everitoken.sdk.java.abi;

@FunctionalInterface
public interface AbiSerialisationProvider {
    String serialize(String data);
}
