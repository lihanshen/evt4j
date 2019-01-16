package io.everitoken.sdk.java.params;

import io.everitoken.sdk.java.exceptions.InvalidPublicKeyException;
import org.json.JSONObject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class FungibleBalanceParamsTest {

    private final String validPublicKey = "EVT76uLwUD5t6fkob9Rbc9UxHgdTVshNceyv2hmppw4d82j2zYRpa";
    private final String inValidPublicKey = "EVT76uLwUD5t6fkob9Rbc11UxHgdTVshNceyv2hmppw4d82j2zYRpa";

    @Test
    @DisplayName("Don't throw exceptions when valid public key is passed")
    void failWhenInvalidPublicKeyPassed() {
        Assertions.assertDoesNotThrow(() -> {
            new FungibleBalanceParams(validPublicKey, null);
        });
    }

    @Test
    @DisplayName("Throws when invalid public key is passed")
    void invalidPublicKeyPassed() {
        Assertions.assertThrows(InvalidPublicKeyException.class, () -> {
            new FungibleBalanceParams(inValidPublicKey, null);
        });
    }

    @Test
    @DisplayName("Don't throw exceptions when valid public key is passed")
    void correctJSON() {
        Assertions.assertDoesNotThrow(() -> {
            String symbolId = "testSymbolId";
            FungibleBalanceParams params = new FungibleBalanceParams(validPublicKey, symbolId);
            JSONObject json = params.asJson();
            assertTrue(json.getString("address").equals(validPublicKey), "correct address");
            assertTrue(json.getString("sym_id").equals(symbolId), "correct symbol id");
        });
    }

    @Test
    @DisplayName("With symbol ID, not throw exceptions when valid public key is passed")
    void optionalSymbolId() {
        Assertions.assertDoesNotThrow(() -> {
            FungibleBalanceParams params = new FungibleBalanceParams(validPublicKey);
            JSONObject json = params.asJson();
            assertTrue(json.getString("address").equals(validPublicKey), "correct address");
            assertFalse(json.has("sym_id"), "correct symbol id");
        });
    }
}