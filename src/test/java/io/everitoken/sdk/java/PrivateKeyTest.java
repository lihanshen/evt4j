package io.everitoken.sdk.java;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PrivateKeyTest {
    @Test
    public void toPublicKey() {
        Assertions.assertDoesNotThrow(() -> {
            String wif = "5JV1kctxPzU3BdRENgRyDcUWQSqqzeckzjKXJWSkBoxXmXUCqKB";
            PublicKey publicKey = PrivateKey.fromWif(wif).toPublicKey();
            assertEquals(publicKey.toString(), "EVT5cd4a3RyaVoubc4w3j3Z3YvCJgtKZPRdJHDdk7wVsMbc3yEH5U");
        });
    }

    @Test
    public void seedPrivateKey() {
        Assertions.assertDoesNotThrow(() -> {
            String publicKeyStr = "EVT6Qz3wuRjyN6gaU3P3XRxpnEZnM4oPxortemaWDwFRvsv2FxgND";
            String privateKey = "5J1by7KRQujRdXrurEsvEr2zQGcdPaMJRjewER6XsAR2eCcpt3D";
            PrivateKey key = PrivateKey.seedPrivateKey("seed");

            PublicKey publicKey = key.toPublicKey();
            assertEquals(publicKey.toString(), publicKeyStr);
            assertEquals(key.toWif(), privateKey);
        });
    }

    @Test
    public void isValidPrivateKey() {
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