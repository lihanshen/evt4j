package io.everitoken.sdk.java.keyProvider;

import io.everitoken.sdk.java.EvtSdkException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class SingleKeyProviderTest {
    public final String validPrivateKey = "5JswhuLDEq7BENcNsu41Eg7dZCiv4TG8WffTNvbexbC1tyCN9EU";
    private final String invalidPrivateKey = "5J1by7KRQujRdXrurEsvEr2zQGcdPaMJRjewER7XsAR2eCcpt3D";

    @Test
    @DisplayName("Valid private key doesn't throw exception")
    public void initWithValidPrivateKey() {
        Assertions.assertDoesNotThrow(() -> {
             new SingleKeyProvider(validPrivateKey);
        });
    }

    @Test
    @DisplayName("Invalid private key throws exception")
    public void initWithInvalidPrivateKeyGetException() {
        Assertions.assertThrows(EvtSdkException.class, () -> {
            new SingleKeyProvider(invalidPrivateKey);
        });
    }
}