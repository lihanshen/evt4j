package io.everitoken.sdk.java;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SignatureTest {

    @Test
    @DisplayName("Sign and verify")
    void SignAndVerify() {
        Assertions.assertDoesNotThrow(() -> {
            String message = "helloworld";
            PrivateKey key = PrivateKey.fromWif("5JV1kctxPzU3BdRENgRyDcUWQSqqzeckzjKXJWSkBoxXmXUCqKB");
            Signature sig = Signature.sign(message.getBytes(), key);

            boolean verifyResult = Signature.verify(message.getBytes(), sig, key.toPublicKey());
            assertTrue(verifyResult, "Able to verify");
        });
    }

    @Test
    @DisplayName("sign and signHash produces same result")
    void signAndSignHash() {
        Assertions.assertDoesNotThrow(() -> {
            String message = "helloworldwhatnot";
            PrivateKey key = PrivateKey.fromWif("5JV1kctxPzU3BdRENgRyDcUWQSqqzeckzjKXJWSkBoxXmXUCqKB");
            Signature sig = Signature.sign(message.getBytes(), key);
            Signature sig1 = Signature.signHash(Utils.hashTwice(message.getBytes()), key);

            assertEquals(sig, sig1);
        });
    }

    @Test
    @DisplayName("signHash and verifyHash should only work with byte[] of length 32")
    void invalidHashLength() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            PrivateKey key = PrivateKey.fromWif("5JV1kctxPzU3BdRENgRyDcUWQSqqzeckzjKXJWSkBoxXmXUCqKB");
            Signature.signHash(new byte[]{}, key);
        });

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            String message = "someOtherMessage";
            PrivateKey key = PrivateKey.fromWif("5JV1kctxPzU3BdRENgRyDcUWQSqqzeckzjKXJWSkBoxXmXUCqKB");
            Signature sig = Signature.sign(message.getBytes(), key);
            Signature.verifyHash(new byte[]{}, sig, key.toPublicKey());
        });
    }

    @Test
    @DisplayName("verify and verifyHash")
    void verifyAndVerifyHash() {
        Assertions.assertDoesNotThrow(() -> {
            String message = "helloworld";
            PrivateKey key = PrivateKey.fromWif("5JV1kctxPzU3BdRENgRyDcUWQSqqzeckzjKXJWSkBoxXmXUCqKB");
            Signature sig = Signature.sign(message.getBytes(), key);
            Signature sig1 = Signature.signHash(Utils.hashTwice(message.getBytes()), key);

            boolean verifyResult = Signature.verify(message.getBytes(), sig1, key.toPublicKey());
            boolean verifyResult1 = Signature.verifyHash(Utils.hashTwice(message.getBytes()), sig, key.toPublicKey());
            assertTrue(verifyResult, "Able to verify");
            assertTrue(verifyResult1, "Able to verify");
        });
    }

    @Test
    @DisplayName("Signature has Recover Id")
    void SignatureHasRecId() {
        Assertions.assertDoesNotThrow(() -> {
            String message = "helloworld";
            PrivateKey key = PrivateKey.fromWif("5JV1kctxPzU3BdRENgRyDcUWQSqqzeckzjKXJWSkBoxXmXUCqKB");
            Signature sig = Signature.sign((message.getBytes()), key);
            assertEquals(sig.getRecId(), 0, "recId equals either 0 or 1");
        });
    }

    @Test
    @DisplayName("Can recover public key from signature")
    void RecoverPublicKeyFromSignatureSuccessful() {
        Assertions.assertDoesNotThrow(() -> {
            String message = "helloworld";
            PrivateKey key = PrivateKey.fromWif("5JV1kctxPzU3BdRENgRyDcUWQSqqzeckzjKXJWSkBoxXmXUCqKB");
            Signature sig = Signature.sign((message.getBytes()), key);

            PublicKey publicKey = Signature.recoverPublicKey(Utils.hashTwice(message.getBytes()), sig);

            assertTrue(PublicKey.isValidPublicKey(publicKey.toString()));
            assertEquals(publicKey.toString(), key.toPublicKey().toString());
        });
    }

    @Test
    @DisplayName("Return false when public key recovered from signature doesn't match")
    void RecoverPublicKeyFromSignatureFailed() {
        Assertions.assertDoesNotThrow(() -> {
            String message = "helloworld";
            PrivateKey key = PrivateKey.fromWif("5JV1kctxPzU3BdRENgRyDcUWQSqqzeckzjKXJWSkBoxXmXUCqKB");
            Signature sig = Signature.sign((message.getBytes()), key);

            String wrongMessage = "foobar";
            PublicKey publicKey = Signature.recoverPublicKey(Utils.hashTwice(wrongMessage.getBytes()), sig);
            assertNotEquals(publicKey.toString(), key.toPublicKey().toString());
        });
    }
}