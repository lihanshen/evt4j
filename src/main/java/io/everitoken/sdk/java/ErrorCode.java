package io.everitoken.sdk.java;

public enum ErrorCode {
    WIF_FORMAT_INVALID(5000, "Wif format is invalid.", Category.KEY),
    PUBLIC_KEY_INVALID(5003, "Public key is not valid.", Category.KEY),
    REC_ID_NOT_FOUND(5004, "RecID for public key recover is not found.", Category.KEY),
    RECOVER_PUB_FROM_SIG_FAILURE(5005, "Failed to recover public key from signature with the data passed in.",
                                 Category.KEY
    ),
    BASE58_CHECK_FAILURE(5006, "Base58 check failed", Category.KEY),
    API_RESOURCE_FAILURE(6000, "Failed to fetch the api resource", Category.API);

    private final int value;
    private final String msg;
    private final Category category;

    private enum Category {
        KEY("Key"), API("Api");

        private final String name;

        Category(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }
    }

    ErrorCode(int value, String msg, Category category) {
        this.value = value;
        this.msg = msg;
        this.category = category;
    }

    public int getValue() {
        return value;
    }

    public String getCategory() {
        return category.getName();
    }

    public String getMsg() {
        return msg;
    }
}

