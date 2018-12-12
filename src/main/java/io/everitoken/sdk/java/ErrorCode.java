package io.everitoken.sdk.java;


enum ErrorCode {
    WIF_FORMAT_INVALID(5000, "Wif format is invalid."),
    SIGN_PRIVATE_KEY_INIT_FAILURE(5001, "Failed to initialize private key for signing."),
    PUBLIC_KEY_INVALID(5003, "Public key is not valid."),
    ;

    private final int value;
    private final String msg;

    ErrorCode(int value, String msg) {
        this.value = value;
        this.msg = msg;
    }

    public int getValue() {
        return value;
    }

    public String getMsg() {
        return msg;
    }
}

