package io.everitoken.sdk.java;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class SignatureTest {

    @Test
    @DisplayName("Sign and verify")
    public void SignAndVerify() {
        Assertions.assertDoesNotThrow(() -> {
            String message = "helloworld";
            PrivateKey key = PrivateKey.fromWif("5JV1kctxPzU3BdRENgRyDcUWQSqqzeckzjKXJWSkBoxXmXUCqKB");
            Signature sig = Signature.sign((message.getBytes()), key);

            boolean verifyResult = Signature.verify(message.getBytes(), sig, key.toPublicKey());
            assertTrue(verifyResult, "Able to verify");
        });
    }

    @Test
    @DisplayName("Signature has Recover Id")
    public void SignatureHasRecId() {
        Assertions.assertDoesNotThrow(() -> {
            String message = "helloworld";
            PrivateKey key = PrivateKey.fromWif("5JV1kctxPzU3BdRENgRyDcUWQSqqzeckzjKXJWSkBoxXmXUCqKB");
            Signature sig = Signature.sign((message.getBytes()), key);
            assertTrue(sig.getRecId() == 0, "recId equals either 0 or 1");
        });
    }

    @Test
    @DisplayName("Can recover public key from signature")
    public void RecoverPublicKeyFromSignatureSuccessful() {
        Assertions.assertDoesNotThrow(() -> {
            String message = "helloworld";
            PrivateKey key = PrivateKey.fromWif("5JV1kctxPzU3BdRENgRyDcUWQSqqzeckzjKXJWSkBoxXmXUCqKB");
            Signature sig = Signature.sign((message.getBytes()), key);

            PublicKey publicKey = Signature.recoverPublicKey(message.getBytes(), sig);

            assertTrue(PublicKey.isValidPublicKey(publicKey.toString()));
        });
    }

    @Test
    @DisplayName("Return false when public key recovered from signature doesn't match")
    public void RecoverPublicKeyFromSignatureFailed() {
        Assertions.assertDoesNotThrow(() -> {
            String message = "helloworld";
            PrivateKey key = PrivateKey.fromWif("5JV1kctxPzU3BdRENgRyDcUWQSqqzeckzjKXJWSkBoxXmXUCqKB");
            Signature sig = Signature.sign((message.getBytes()), key);

            String wrongMessage = "foobar";
            PublicKey publicKey = Signature.recoverPublicKey(wrongMessage.getBytes(), sig);
            assertFalse(publicKey.toString().equals(key.toPublicKey().toString()));
        });
    }
}