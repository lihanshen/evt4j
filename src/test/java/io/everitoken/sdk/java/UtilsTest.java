package io.everitoken.sdk.java;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class UtilsTest {

    @Test
    @DisplayName("Base58 Invalid Key")
    void base58CheckDecodeWithInvalidKey() {
        Assertions.assertThrows(EvtSdkException.class, () -> {
            String key = "76uLwUD5t6fkob9Rbc11UxHgdTVshNceyv2hmppw4d82j2zYRpa";
            Utils.base58CheckDecode(key);
        });
    }

    @Test
    @DisplayName("Base58 Valid Key, Checksum successful")
    void base58CheckDecodeWithValidKey() {
        Assertions.assertDoesNotThrow(() -> {
            Utils.base58CheckDecode("76uLwUD5t6fkob9Rbc9UxHgdTVshNceyv2hmppw4d82j2zYRpa");
        });
    }

    @Test
    void random32BytesAsHex() {
        String str32BytesInHex = Utils.random32BytesAsHex();
        assertEquals(64, str32BytesInHex.length(), "Message should be 64 characters");
    }

    @Test
    void random32Bytes() {
        byte[] bytes32 = Utils.random32Bytes();
        assertEquals(32, bytes32.length, "Message should be 32 bytes long");
    }
}