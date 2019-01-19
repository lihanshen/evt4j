package io.everitoken.sdk.java.params;

import io.everitoken.sdk.java.PublicKey;
import org.json.JSONObject;

import javax.annotation.Nullable;

public class FungibleBalanceParams implements ApiParams {
    private final PublicKey publicKey;
    private final String symbolId;

    public FungibleBalanceParams(String publicKey, @Nullable String symbolId) {
        this.publicKey = PublicKey.of(publicKey);
        this.symbolId = symbolId;
    }

    public FungibleBalanceParams(String publicKey) {
        this(publicKey, null);
    }

    @Override
    public JSONObject asJson() {
        JSONObject jsonObject = new JSONObject();

        jsonObject.put("address", publicKey.toString());

        if (symbolId != null) {
            jsonObject.put("sym_id", symbolId);
        }

        return jsonObject;
    }
}
