package io.everitoken.sdk.java;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class UtilsTest {
    @Rule
    public final ExpectedException exception = ExpectedException.none();

    @Test
    public void base58CheckDecodeWithInvalidKey() {
        String key = "76uLwUD5t6fkob9Rbc11UxHgdTVshNceyv2hmppw4d82j2zYRpa";
        boolean hasError = false;
        try {
            Utils.base58CheckDecode(key);
        } catch (Exception ex) {
            hasError = true;
        }

        assertTrue("Checksum failed", hasError);
    }

    @Test
    public void base58CheckDecodeWithValidKey() {
        String key = "76uLwUD5t6fkob9Rbc9UxHgdTVshNceyv2hmppw4d82j2zYRpa";
        boolean hasError = false;
        try {
            Utils.base58CheckDecode(key);
        } catch (Exception ex) {
            hasError = true;
        }

        assertFalse("Checksum successful", hasError);
    }

    @Test
    public void random32BytesAsHex() {
        String str32BytesInHex = Utils.random32BytesAsHex();
        assertTrue("Message should be 64 characters", str32BytesInHex.length() == 64);
    }

    @Test
    public void random32Bytes() {
        byte[] bytes32 = Utils.random32Bytes();
        assertTrue("Message should be 32 bytes long", bytes32.length == 32);
    }
}