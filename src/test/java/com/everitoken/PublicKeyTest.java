package com.everitoken;

import org.junit.Test;

import static org.junit.Assert.*;

public class PublicKeyTest {

    @Test
    public void isValidPublicKeyPrefix() {
        String key = "SOMEOTHERKEY";
        assertFalse("Invalid key without EVT prefix", PublicKey.isValidPublicKey(key));
    }

    @Test
    public void isValidPublicKeyInvalidKey() {
        String key = "EVT76uLwUD5t6fkob9Rbc11UxHgdTVshNceyv2hmppw4d82j2zYRpa";
        assertFalse("Invalid key", PublicKey.isValidPublicKey(key));
    }

    @Test
    public void isValidPublicKeyValidKey() {
        String key = "EVT76uLwUD5t6fkob9Rbc9UxHgdTVshNceyv2hmppw4d82j2zYRpa";
        assertTrue("valid key", PublicKey.isValidPublicKey(key));
    }

    @Test
    public void isValidPublicKeyWithInvalidKey() {
        String key = "EVT76uLwUD5t6fkob9Rbc11UxHgdTVshNceyv2hmppw4d82j2zYRpa";
        assertFalse("invalid key", PublicKey.isValidPublicKey(key));
    }
}