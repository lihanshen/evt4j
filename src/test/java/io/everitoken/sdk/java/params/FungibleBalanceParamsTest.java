package io.everitoken.sdk.java.params;

import org.json.JSONObject;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class FungibleBalanceParamsTest {

    private final String validPublicKey = "EVT76uLwUD5t6fkob9Rbc9UxHgdTVshNceyv2hmppw4d82j2zYRpa";
    private final String inValidPublicKey = "EVT76uLwUD5t6fkob9Rbc11UxHgdTVshNceyv2hmppw4d82j2zYRpa";

    @Test
    public void failWhenInvalidPublickeyPassed() {
        boolean throwException = false;
        try {
            FungibleBalanceParams params = new FungibleBalanceParams(validPublicKey, null);
        } catch (Exception ex) {
            throwException = true;
        }

        assertFalse(throwException, "Don't throw exceptions when valid public key is passed" );
    }

    @Test
    public void correctJSON() {
        boolean throwException = false;
        String symbolId = "testSymbolId";
        try {
            FungibleBalanceParams params = new FungibleBalanceParams(validPublicKey, symbolId);
            JSONObject json = params.asJson();
            assertTrue(json.getString("address").equals(validPublicKey), "correct address" );
            assertTrue(json.getString("sym_id").equals(symbolId), "correct symbol id" );

        } catch (Exception ex) {
            throwException = true;
        }

        assertFalse(throwException, "Don't throw exceptions when valid public key is passed" );
    }

    @Test
    public void optionalSymbolId() {
        boolean throwException = false;

        try {
            FungibleBalanceParams params = new FungibleBalanceParams(validPublicKey);
            JSONObject json = params.asJson();
            assertTrue(json.getString("address").equals(validPublicKey), "correct address");
            assertFalse(json.has("sym_id"), "correct symbol id" );

        } catch (Exception ex) {
            throwException = true;
        }

        assertFalse(throwException, "Don't throw exceptions when valid public key is passed");
    }
}