package io.everitoken.sdk.java.exceptions;

public class Base58CheckException extends IllegalArgumentException implements EvtException {
    public Base58CheckException() {
    }

    public Base58CheckException(String message, Throwable ex) {
        super(message, ex);
    }
}
