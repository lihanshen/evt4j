package io.everitoken.sdk.java.exceptions;

public class InvalidPublicKeyException extends IllegalArgumentException implements EvtException {
    public InvalidPublicKeyException(String publicKey) {
        super(String.format("Public key: '%s' is invalid", publicKey));
    }

    public InvalidPublicKeyException() {
        super("Public key is not valid.");
    }
}
