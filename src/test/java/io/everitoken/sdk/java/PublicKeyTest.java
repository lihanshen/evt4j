package io.everitoken.sdk.java;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import io.everitoken.sdk.java.exceptions.InvalidPublicKeyException;

class PublicKeyTest {
    private final String validPublicKey = "EVT76uLwUD5t6fkob9Rbc9UxHgdTVshNceyv2hmppw4d82j2zYRpa";
    private final String inValidPublicKey = "EVT76uLwUD5t6fkob9Rbc11UxHgdTVshNceyv2hmppw4d82j2zYRpa";

    @Test
    @DisplayName("No exception is thrown with valid public key")
    void testConstructorWithValidPublicKey() {
        Assertions.assertDoesNotThrow(() -> PublicKey.of(validPublicKey));
    }

    @Test
    @DisplayName("Exception is thrown with invalid public key")
    void testConstructorWithInValidPublicKey() {
        Assertions.assertThrows(InvalidPublicKeyException.class, () -> PublicKey.of(inValidPublicKey));
    }

    @Test
    void isValidPublicKeyPrefix() {
        String key = "SOMEOTHERKEY";
        assertFalse(PublicKey.isValidPublicKey(key), "Invalid key without EVT prefix");
    }

    @Test
    void isValidPublicKeyInvalidKey() {
        assertFalse(PublicKey.isValidPublicKey(inValidPublicKey), "Invalid key");
        assertFalse(PublicKey.isValidPublicKey("EVT00000000000000000000000000000000000000000000000000"),
                "Null Address is invalid");
    }

    @Test
    void isValidPublicKeyValidKey() {
        assertTrue(PublicKey.isValidPublicKey(validPublicKey), "valid key");
    }

    @Test
    void isValidPublicKeyWithInvalidKey() {
        assertFalse(PublicKey.isValidPublicKey(inValidPublicKey), "invalid key");
    }
}