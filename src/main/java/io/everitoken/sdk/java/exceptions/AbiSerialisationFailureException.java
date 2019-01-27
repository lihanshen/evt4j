package io.everitoken.sdk.java.exceptions;

public class AbiSerialisationFailureException extends IllegalArgumentException implements EvtException {
    public AbiSerialisationFailureException(String message, Throwable cause) {
        super(String.format("Failed to serialize Abi via Api due to (%s)", message), cause);
    }
}
