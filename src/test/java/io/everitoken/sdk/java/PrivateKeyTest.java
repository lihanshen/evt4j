package io.everitoken.sdk.java;

import io.everitoken.sdk.java.exceptions.WifFormatException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PrivateKeyTest {
    @Test
    @DisplayName("Invalid wif will throw exception")
    void newPrivateKeyWithInvalidWif() {
        Assertions.assertThrows(WifFormatException.class, () -> {
            PrivateKey.of("");
        });
    }

    @Test
    @DisplayName("Able to create public key from valid wif")
    void toPublicKey() {
        Assertions.assertDoesNotThrow(() -> {
            String wif = "5JV1kctxPzU3BdRENgRyDcUWQSqqzeckzjKXJWSkBoxXmXUCqKB";
            PublicKey publicKey = PrivateKey.of(wif).toPublicKey();
            assertEquals("EVT5cd4a3RyaVoubc4w3j3Z3YvCJgtKZPRdJHDdk7wVsMbc3yEH5U", publicKey.toString());
        });
    }

    @Test
    @DisplayName("Generate correct private key from seed")
    void seedPrivateKey() {
        Assertions.assertDoesNotThrow(() -> {
            String publicKeyStr = "EVT6Qz3wuRjyN6gaU3P3XRxpnEZnM4oPxortemaWDwFRvsv2FxgND";
            String privateKey = "5J1by7KRQujRdXrurEsvEr2zQGcdPaMJRjewER6XsAR2eCcpt3D";
            PrivateKey key = PrivateKey.seedPrivateKey("seed");

            PublicKey publicKey = key.toPublicKey();
            assertEquals(publicKeyStr, publicKey.toString());
            assertEquals(privateKey, key.toWif());
        });
    }

    @Test
    @DisplayName("Check validity of a private key")
    void isValidPrivateKey() {
        assertTrue(
                PrivateKey.isValidPrivateKey("5J1by7KRQujRdXrurEsvEr2zQGcdPaMJRjewER6XsAR2eCcpt3D"),
                "Valid private key"
        );
        assertFalse(
                PrivateKey.isValidPrivateKey("5J1by7KRQujRdXrurEsvEr2zQGcdPaMJRjewER7XsAR2eCcpt3D"),
                "Invalid private key"
        );
    }
}