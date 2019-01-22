package io.everitoken.sdk.java.exceptions;

public class InvalidFungibleBalanceException extends IllegalArgumentException implements EvtException {
    public InvalidFungibleBalanceException(String message, Throwable cause) {
        super(message, cause);
    }
}
