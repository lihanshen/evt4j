package io.everitoken.sdk.java.params;

import org.json.JSONObject;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

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

        assertFalse("Don't throw exceptions when valid public key is passed", throwException);
    }

    @Test
    public void correctJSON() {
        boolean throwException = false;
        String symbolId = "testSymbolId";
        try {
            FungibleBalanceParams params = new FungibleBalanceParams(validPublicKey, symbolId);
            JSONObject json = params.asJson();
            assertTrue("correct address", json.getString("address").equals(validPublicKey));
            assertTrue("correct symbol id", json.getString("sym_id").equals(symbolId));

        } catch (Exception ex) {
            throwException = true;
        }

        assertFalse("Don't throw exceptions when valid public key is passed", throwException);
    }

    @Test
    public void optionalSymbolId() {
        boolean throwException = false;

        try {
            FungibleBalanceParams params = new FungibleBalanceParams(validPublicKey);
            JSONObject json = params.asJson();
            assertTrue("correct address", json.getString("address").equals(validPublicKey));
            assertFalse("correct symbol id", json.has("sym_id"));

        } catch (Exception ex) {
            throwException = true;
        }

        assertFalse("Don't throw exceptions when valid public key is passed", throwException);
    }
}