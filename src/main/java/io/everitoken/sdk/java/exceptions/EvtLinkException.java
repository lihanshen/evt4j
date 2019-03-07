package io.everitoken.sdk.java.exceptions;

public class EvtLinkException extends IllegalArgumentException implements EvtException {
    public EvtLinkException(String message, Throwable cause) {
        super(message, cause);
    }

    public EvtLinkException(String message) {
        super(message);
    }
}
