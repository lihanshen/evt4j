package io.everitoken.sdk.java;

public enum ErrorCode {
    WIF_FORMAT_INVALID(5000, "Wif format is invalid."),
    PUBLIC_KEY_INVALID(5003, "Public key is not valid."),
    REC_ID_NOT_FOUND(5004, "RecID for public key recover is not found."),
    RECOVER_PUB_FROM_SIG_FAILURE(5005, "Failed to recover public key from signature with the data passed in."),
    BASE58_CHECK_FAILURE(5006, "Base58 check failed."),
    API_RESPONSE_FAILURE(5007, "Api response error.");

    private final int value;
    private final String message;

    ErrorCode(int value, String message) {
        this.value = value;
        this.message = message;
    }

    public int getValue() {
        return value;
    }

    public String getMsg() {
        return message;
    }
}

