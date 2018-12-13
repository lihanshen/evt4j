package io.everitoken.sdk.java;

import org.bitcoinj.core.Sha256Hash;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class SignatureTest {

    @Test
    public void SignAndVerify() {
        try {
            String message = "helloworld";
            PrivateKey key = PrivateKey.fromWif("5JV1kctxPzU3BdRENgRyDcUWQSqqzeckzjKXJWSkBoxXmXUCqKB");
            Signature sig = Signature.signHash(Sha256Hash.hash(message.getBytes()), key);

            boolean verifyResult = Signature.verify(Sha256Hash.hash(message.getBytes()), sig, key.toPublicKey());
            assertTrue("Able to verify", verifyResult);
        } catch (Exception ex) {

        }
    }
}