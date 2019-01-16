package io.everitoken.sdk.java;

import io.everitoken.sdk.java.exceptions.InvalidPublicKeyException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PublicKeyTest {
    private final String validPublicKey = "EVT76uLwUD5t6fkob9Rbc9UxHgdTVshNceyv2hmppw4d82j2zYRpa";
    private final String inValidPublicKey = "EVT76uLwUD5t6fkob9Rbc11UxHgdTVshNceyv2hmppw4d82j2zYRpa";

    @Test
    @DisplayName("No exception is thrown with valid public key")
    void testConstructorWithValidPublicKey() {
        Assertions.assertDoesNotThrow(() -> new PublicKey(validPublicKey));
    }

    @Test
    @DisplayName("Exception is thrown with invalid public key")
    void testConstructorWithInValidPublicKey() {
        Assertions.assertThrows(InvalidPublicKeyException.class, () -> new PublicKey(inValidPublicKey));
    }

    @Test
    void isValidPublicKeyPrefix() {
        String key = "SOMEOTHERKEY";
        assertFalse(PublicKey.isValidPublicKey(key), "Invalid key without EVT prefix");
    }

    @Test
    void isValidPublicKeyInvalidKey() {
        assertFalse(PublicKey.isValidPublicKey(inValidPublicKey), "Invalid key");
    }

    @Test
    void isValidPublicKeyValidKey() {
        assertTrue(PublicKey.isValidPublicKey(validPublicKey), "valid key");
    }

    @Test
    void isValidPublicKeyWithInvalidKey() {
        assertFalse(PublicKey.isValidPublicKey(inValidPublicKey), "invalid key");
    }

    @Test
    void getNullAddress() {
        String nullAddress = PublicKey.getNullAddress();
        assertEquals(nullAddress, "EVT00000000000000000000000000000000000000000000000000");
    }

    @Test
    @DisplayName("Valid Public keys and addresses")
    void isValidAddress() {
        String nullAddress = PublicKey.getNullAddress();
        assertTrue(PublicKey.isValidAddress(nullAddress));
        assertFalse(PublicKey.isValidPublicKey(nullAddress));
        assertTrue(PublicKey.isValidAddress("EVT76uLwUD5t6fkob9Rbc9UxHgdTVshNceyv2hmppw4d82j2zYRpa"));
        assertFalse(PublicKey.isValidAddress("EOS6Qz3wuRjyN6gaU3P3XRxpnEZnM4oPxortemaWDwFRvsv2FxgND"));
        assertFalse(PublicKey.isValidAddress("EVT6Qz3wuRjyN6gaU3P3XRxpnEZnM4oPxortemaWDWFRvsv2FxgND"));
        assertTrue(PublicKey.isValidAddress("EVT0000005ZbVoKRDdy6N4r22sSn3WDyB4YkTcf5R1dSjAsmRnFEF"));
    }
}