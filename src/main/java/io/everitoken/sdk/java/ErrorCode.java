package io.everitoken.sdk.java;

public enum ErrorCode {
    WIF_FORMAT_INVALID(5000, "Wif format is invalid."),
    SIGN_PRIVATE_KEY_INIT_FAILURE(5001, "Failed to initialize private key for signing."),
    PUBLIC_KEY_INVALID(5003, "Public key is not valid."),
    REC_ID_NOT_FOUND(5004, "RecID for public key recover is not found."),
    RECOVER_PUB_FROM_SIG_FAILURE(5005, "Failed to recover public key from signature with the data passed in."),
    BASE58_CHECK_FAILURE(5006, "Base58 check failed");

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

