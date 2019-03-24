package io.everitoken.sdk.java.abi;

public interface AbiActionInterface {
	public String serialize(AbiSerialisationProviderInterface provider);

	String getKey();

	String getName();

	String getDomain();

	String getData(AbiSerialisationProviderInterface provider);
}
