package io.everitoken.sdk.java;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class SignatureTest {

    @Test
    public void SignAndVerify() {
        try {
            String message = "helloworld";
            PrivateKey key = PrivateKey.fromWif("5JV1kctxPzU3BdRENgRyDcUWQSqqzeckzjKXJWSkBoxXmXUCqKB");
            Signature sig = Signature.sign((message.getBytes()), key);

            boolean verifyResult = Signature.verify(message.getBytes(), sig, key.toPublicKey());
            assertTrue(verifyResult, "Able to verify");
        } catch (Exception ex) {

        }
    }

    @Test
    public void SignatureHasRecId() {
        boolean hasError = false;
        try {
            String message = "helloworld";
            PrivateKey key = PrivateKey.fromWif("5JV1kctxPzU3BdRENgRyDcUWQSqqzeckzjKXJWSkBoxXmXUCqKB");
            Signature sig = Signature.sign((message.getBytes()), key);
            assertTrue(sig.getRecId() == 0, "recId equals either 0 or 1");
        } catch (EvtSdkException ex) {
            assertTrue(hasError, "No exception is throw");
        }
    }

    @Test
    public void RecoverPublicKeyFromSignatureSuccessful() {
        boolean hasError = false;
        try {
            String message = "helloworld";
            PrivateKey key = PrivateKey.fromWif("5JV1kctxPzU3BdRENgRyDcUWQSqqzeckzjKXJWSkBoxXmXUCqKB");
            Signature sig = Signature.sign((message.getBytes()), key);

            PublicKey publicKey = Signature.recoverPublicKey(message.getBytes(), sig);

            assertTrue(PublicKey.isValidPublicKey(publicKey.toString()));
        } catch (Exception ex) {
            assertTrue(hasError, "No exception is throw");
        }
    }

    @Test
    public void RecoverPublicKeyFromSignatureFailed() {
        try {
            String message = "helloworld";
            PrivateKey key = PrivateKey.fromWif("5JV1kctxPzU3BdRENgRyDcUWQSqqzeckzjKXJWSkBoxXmXUCqKB");
            Signature sig = Signature.sign((message.getBytes()), key);

            String wrongMessage = "foobar";
            PublicKey publicKey = Signature.recoverPublicKey(wrongMessage.getBytes(), sig);
            assertFalse(publicKey.toString().equals(key.toPublicKey().toString()));
        } catch (Exception ex) {
        }
    }
}