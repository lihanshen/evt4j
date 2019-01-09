package io.everitoken.sdk.java;

import javax.annotation.Nullable;

public class EvtSdkException extends Exception {

    private final ErrorCode code;
    private final String meta;

    public EvtSdkException(Throwable cause, ErrorCode code, @Nullable String meta) {
        super(cause);
        this.code = code;
        this.meta = meta;
    }

    public EvtSdkException(Throwable cause, ErrorCode code) {
        super(cause);
        this.code = code;
        meta = null;
    }

    public ErrorCode getCode() {
        return code;
    }

    public String getMeta() {
        return meta;
    }
}
