package io.everitoken.sdk.java;

import org.junit.Test;

import static org.junit.Assert.*;

public class PrivateKeyTest {

    @Test
    public void toPublicKey() {
        String wif = "5JV1kctxPzU3BdRENgRyDcUWQSqqzeckzjKXJWSkBoxXmXUCqKB";
        try {
            PublicKey publicKey = PrivateKey.fromWif(wif).toPublicKey();
            assertEquals(publicKey.getEncoded(true), "EVT5cd4a3RyaVoubc4w3j3Z3YvCJgtKZPRdJHDdk7wVsMbc3yEH5U");
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
            assertEquals(publicKey.getEncoded(true), publicKeyStr);
            assertEquals(key.toWif(), privateKey);
        } catch (Exception ex) {
        }
    }

    @Test
    public void isValidPrivateKey() {
        assertTrue(
                "Valid private key",
                PrivateKey.isValidPrivateKey("5J1by7KRQujRdXrurEsvEr2zQGcdPaMJRjewER6XsAR2eCcpt3D")
        );
        assertFalse(
                "Invalid private key",
                PrivateKey.isValidPrivateKey("5J1by7KRQujRdXrurEsvEr2zQGcdPaMJRjewER7XsAR2eCcpt3D")
        );
    }

    @Test
    public void randomPrivateKey() {
        PrivateKey key = PrivateKey.randomPrivateKey();

    }
}