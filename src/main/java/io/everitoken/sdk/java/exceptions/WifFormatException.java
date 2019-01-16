package io.everitoken.sdk.java.exceptions;

public class WifFormatException extends IllegalArgumentException implements EvtException {
    private static final String message = "Invalid wif format";

    public WifFormatException(String message) {
        super(message);
    }

    public WifFormatException(String message, Throwable ex) {
        super(message, ex);
    }

}
