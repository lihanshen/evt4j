package io.everitoken.sdk.java.keyProvider;

import io.everitoken.sdk.java.EvtSdkException;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class SingleKeyProviderTest {
    public final String validPrivateKey = "5JswhuLDEq7BENcNsu41Eg7dZCiv4TG8WffTNvbexbC1tyCN9EU";
    private final String invalidPrivateKey = "5J1by7KRQujRdXrurEsvEr2zQGcdPaMJRjewER7XsAR2eCcpt3D";

    @Test
    public void initWithValidPrivateKey() {
        boolean hasException = false;
        try {
            SingleKeyProvider keyProvider = new SingleKeyProvider(validPrivateKey);
        } catch (Exception ex) {
            hasException = true;
        }

        assertTrue("Valid private key don't throw exception", !hasException);
    }

    @Test
    public void initWithInvalidPrivateKeyGetException() {
        boolean hasException = false;
        Exception exception = null;
        try {
            SingleKeyProvider keyProvider = new SingleKeyProvider(invalidPrivateKey);
        } catch (Exception ex) {
            exception = ex;
            hasException = true;
        }
        assertTrue("Invalid private key throws exception", hasException);
        assertTrue("Error is an instance of EvtSdkException", exception instanceof EvtSdkException);
    }
}