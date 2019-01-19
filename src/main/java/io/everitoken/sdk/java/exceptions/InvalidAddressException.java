package io.everitoken.sdk.java.exceptions;

public class InvalidAddressException extends IllegalArgumentException implements EvtException {
    public InvalidAddressException(String address) {
        super(String.format("Address: '%s' is invalid", address));
    }

    public InvalidAddressException() {
        super("The address passed in is not valid.");
    }
}
