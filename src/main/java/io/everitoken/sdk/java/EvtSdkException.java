package io.everitoken.sdk.java;

public class EvtSdkException extends Exception {

    private final ErrorCode code;

    public EvtSdkException(Throwable cause, ErrorCode code) {
        super(cause);
        this.code = code;
    }

    public ErrorCode getCode() {
        return code;
    }
}
