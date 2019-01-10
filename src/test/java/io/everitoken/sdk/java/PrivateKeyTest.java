package io.everitoken.sdk.java;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PrivateKeyTest {
    @Test
    public void toPublicKey() {
        String wif = "5JV1kctxPzU3BdRENgRyDcUWQSqqzeckzjKXJWSkBoxXmXUCqKB";
        try {
            PublicKey publicKey = PrivateKey.fromWif(wif).toPublicKey();
            assertEquals(publicKey.toString(), "EVT5cd4a3RyaVoubc4w3j3Z3YvCJgtKZPRdJHDdk7wVsMbc3yEH5U");
        } catch (EvtSdkException e) {

        }
    }

    @Test
    public void seedPrivateKey() {
        String publicKeyStr = "EVT6Qz3wuRjyN6gaU3P3XRxpnEZnM4oPxortemaWDwFRvsv2FxgND";
        String privateKey = "5J1by7KRQujRdXrurEsvEr2zQGcdPaMJRjewER6XsAR2eCcpt3D";
        PrivateKey key = PrivateKey.seedPrivateKey("seed");

        try {
            PublicKey publicKey = key.toPublicKey();
            assertEquals(publicKey.toString(), publicKeyStr);
            assertEquals(key.toWif(), privateKey);
        } catch (Exception ex) {
        }
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