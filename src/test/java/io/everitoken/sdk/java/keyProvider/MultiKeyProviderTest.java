package io.everitoken.sdk.java.keyProvider;

import io.everitoken.sdk.java.exceptions.WifFormatException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class MultiKeyProviderTest {
    final String validPrivateKey = "5JswhuLDEq7BENcNsu41Eg7dZCiv4TG8WffTNvbexbC1tyCN9EU";
    final String validPrivateKey1 = "5JswhuLDEq7BENcNsu41Eg7dZCiv4TG8WffTNvbexbC1tyCN9EU";
    private final String invalidPrivateKey = "5J1by7KRQujRdXrurEsvEr2zQGcdPaMJRjewER7XsAR2eCcpt3D";

    @Test
    @DisplayName("Valid private key doesn't throw exception")
    void initWithValidPrivateKey() {
        Assertions.assertDoesNotThrow(() -> {
            String[] keyList = {validPrivateKey, validPrivateKey1};
            new MultiKeyProvider(keyList);
        });
    }

    @Test
    @DisplayName("Invalid private key throws exception")
    void initWithInvalidPrivateKeyGetException() {
        Assertions.assertThrows(WifFormatException.class, () -> {
            String[] keyList = {validPrivateKey, invalidPrivateKey};
            new MultiKeyProvider(keyList);
        });
    }
}