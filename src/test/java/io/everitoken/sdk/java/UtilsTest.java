package io.everitoken.sdk.java;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class UtilsTest {

    @Test
    @DisplayName("Base58 Invalid Key")
    public void base58CheckDecodeWithInvalidKey() {
        String key = "76uLwUD5t6fkob9Rbc11UxHgdTVshNceyv2hmppw4d82j2zYRpa";
        boolean hasError = false;
        try {
            Utils.base58CheckDecode(key);
        } catch (Exception ex) {
            hasError = true;
        }

        assertTrue(hasError, "Checksum failed");
    }

    @Test
    @DisplayName("Base58 Valid Key")
    public void base58CheckDecodeWithValidKey() {
        String key = "76uLwUD5t6fkob9Rbc9UxHgdTVshNceyv2hmppw4d82j2zYRpa";
        boolean hasError = false;
        try {
            Utils.base58CheckDecode(key);
        } catch (Exception ex) {
            hasError = true;
        }

        assertFalse(hasError, "Checksum successful");
    }

    @Test
    @DisplayName("Test")
    public void random32BytesAsHex() {
        String str32BytesInHex = Utils.random32BytesAsHex();
        assertEquals(64, str32BytesInHex.length(), "Message should be 64 characters");
    }

    @Test
    public void random32Bytes() {
        byte[] bytes32 = Utils.random32Bytes();
        assertEquals(32, bytes32.length, "Message should be 32 bytes long");
    }
}