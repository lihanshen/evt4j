package io.everitoken.sdk.java;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.*;

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
}