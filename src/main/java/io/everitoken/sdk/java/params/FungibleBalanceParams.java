package io.everitoken.sdk.java.params;

import io.everitoken.sdk.java.EvtSdkException;
import io.everitoken.sdk.java.PublicKey;
import org.json.JSONObject;

import javax.annotation.Nullable;

public class FungibleBalanceParams implements ApiParams {
    private PublicKey publicKey;
    private String symbolId;

    public FungibleBalanceParams(String publicKey, @Nullable String symbolId) throws EvtSdkException {
        this.publicKey = new PublicKey(publicKey);
        this.symbolId = symbolId;
    }

    public FungibleBalanceParams(String publicKey) throws EvtSdkException {
        this(publicKey, null);
    }

    public JSONObject asJson() {
        JSONObject jsonObject = new JSONObject();

        jsonObject.put("address", publicKey.toString());

        if (symbolId != null) {
            jsonObject.put("sym_id", symbolId);
        }

        return jsonObject;
    }
}
