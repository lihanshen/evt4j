package io.everitoken.sdk.java.exceptions;

public class InvalidSignatureException extends IllegalArgumentException implements EvtException {
    public InvalidSignatureException(String s) {
        super(s);
    }
}
