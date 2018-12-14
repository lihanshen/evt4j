package io.everitoken.sdk.java;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class SignatureTest {

    @Test
    public void SignAndVerify() {
        try {
            String message = "helloworld";
            PrivateKey key = PrivateKey.fromWif("5JV1kctxPzU3BdRENgRyDcUWQSqqzeckzjKXJWSkBoxXmXUCqKB");
            Signature sig = Signature.sign((message.getBytes()), key);

            boolean verifyResult = Signature.verify(message.getBytes(), sig, key.toPublicKey());
            assertTrue("Able to verify", verifyResult);
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
            assertTrue("recId equals either 0 or 1", sig.getRecId() == 0);
        } catch (EvtSdkException ex) {
            assertTrue("No exception is throw", hasError);
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
            assertTrue("No exception is throw", hasError);
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