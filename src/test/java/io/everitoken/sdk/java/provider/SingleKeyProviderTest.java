package io.everitoken.sdk.java.provider;

import io.everitoken.sdk.java.exceptions.WifFormatException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class SingleKeyProviderTest {
    private final String validPrivateKey = "5JswhuLDEq7BENcNsu41Eg7dZCiv4TG8WffTNvbexbC1tyCN9EU";
    private final String invalidPrivateKey = "5J1by7KRQujRdXrurEsvEr2zQGcdPaMJRjewER7XsAR2eCcpt3D";

    @Test
    @DisplayName("Valid private key doesn't throw exception")
    void initWithValidPrivateKey() {
        Assertions.assertDoesNotThrow(() -> {
            new SingleKeyProvider(validPrivateKey);
        });
    }

    @Test
    @DisplayName("Invalid private key throws exception")
    void initWithInvalidPrivateKeyGetException() {
        Assertions.assertThrows(WifFormatException.class, () -> {
            new SingleKeyProvider(invalidPrivateKey);
        });
    }
}