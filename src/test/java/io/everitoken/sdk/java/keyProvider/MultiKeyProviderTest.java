package io.everitoken.sdk.java.keyProvider;

import io.everitoken.sdk.java.EvtSdkException;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class MultiKeyProviderTest {
    public final String validPrivateKey = "5JswhuLDEq7BENcNsu41Eg7dZCiv4TG8WffTNvbexbC1tyCN9EU";
    public final String validPrivateKey1 = "5JswhuLDEq7BENcNsu41Eg7dZCiv4TG8WffTNvbexbC1tyCN9EU";
    private final String invalidPrivateKey = "5J1by7KRQujRdXrurEsvEr2zQGcdPaMJRjewER7XsAR2eCcpt3D";

    @Test
    public void initWithValidPrivateKey() {
        boolean hasException = false;
        try {
            String[] keyList = {validPrivateKey, validPrivateKey1};
            MultiKeyProvider keyProvider = new MultiKeyProvider(keyList);
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
            String[] keyList = {validPrivateKey, invalidPrivateKey};
            MultiKeyProvider keyProvider = new MultiKeyProvider(keyList);
        } catch (Exception ex) {
            exception = ex;
            hasException = true;
        }
        assertTrue("Invalid private key throws exception", hasException);
        assertTrue("Error is an instance of EvtSdkException", exception instanceof EvtSdkException);
    }
}