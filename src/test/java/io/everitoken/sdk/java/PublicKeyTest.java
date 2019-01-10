package io.everitoken.sdk.java;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PublicKeyTest {
    private final String validPublicKey = "EVT76uLwUD5t6fkob9Rbc9UxHgdTVshNceyv2hmppw4d82j2zYRpa";
    private final String inValidPublicKey = "EVT76uLwUD5t6fkob9Rbc11UxHgdTVshNceyv2hmppw4d82j2zYRpa";

    @Test
    public void testConstructorWithValidPublicKey() {
        boolean throwEx = false;
        try {
            PublicKey publicKey = new PublicKey(validPublicKey);
        } catch (EvtSdkException ex) {
            throwEx = true;
        }
        assertTrue(!throwEx, "No exception is thrown with valid public key");
    }

    @Test
    public void testConstructorWithInValidPublicKey() {
        boolean throwEx = false;
        try {
            PublicKey publicKey = new PublicKey(inValidPublicKey);
        } catch (EvtSdkException ex) {
            throwEx = true;
        }
        assertTrue(throwEx, "Exception is thrown with invalid public key");
    }

    @Test
    public void isValidPublicKeyPrefix() {
        String key = "SOMEOTHERKEY";
        assertFalse(PublicKey.isValidPublicKey(key), "Invalid key without EVT prefix");
    }

    @Test
    public void isValidPublicKeyInvalidKey() {
        assertFalse(PublicKey.isValidPublicKey(inValidPublicKey), "Invalid key");
    }

    @Test
    public void isValidPublicKeyValidKey() {
        assertTrue(PublicKey.isValidPublicKey(validPublicKey), "valid key" );
    }

    @Test
    public void isValidPublicKeyWithInvalidKey() {
        assertFalse(PublicKey.isValidPublicKey(inValidPublicKey), "invalid key" );
    }

    @Test
    public void getNullAddress() {
        String nullAddress = PublicKey.getNullAddress();
        assertEquals(nullAddress, "EVT00000000000000000000000000000000000000000000000000");
    }

    @Test
    public void isValidAddress() {
        String nullAddress = PublicKey.getNullAddress();
        assertTrue(PublicKey.isValidAddress(nullAddress));
        assertFalse(PublicKey.isValidPublicKey(nullAddress));
        assertTrue(PublicKey.isValidAddress("EVT76uLwUD5t6fkob9Rbc9UxHgdTVshNceyv2hmppw4d82j2zYRpa"));
        assertFalse(PublicKey.isValidAddress("EOS6Qz3wuRjyN6gaU3P3XRxpnEZnM4oPxortemaWDwFRvsv2FxgND"));
        assertFalse(PublicKey.isValidAddress("EVT6Qz3wuRjyN6gaU3P3XRxpnEZnM4oPxortemaWDWFRvsv2FxgND"));
        assertTrue(PublicKey.isValidAddress("EVT0000005ZbVoKRDdy6N4r22sSn3WDyB4YkTcf5R1dSjAsmRnFEF"));
    }
}