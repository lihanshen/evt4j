package io.everitoken.sdk.java.abi;

@FunctionalInterface
public interface AbiSerialisable {
    String serialize(AbiSerialisationProviderInterface provider);
}
