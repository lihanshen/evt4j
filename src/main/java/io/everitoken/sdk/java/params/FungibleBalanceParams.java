package io.everitoken.sdk.java.params;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.annotation.JSONField;
import io.everitoken.sdk.java.PublicKey;

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

    // TODO cleanup -> remove
    public static void main(String[] args) {
        FungibleBalanceParams params = new FungibleBalanceParams("EVT76uLwUD5t6fkob9Rbc9UxHgdTVshNceyv2hmppw4d82j2zYRpa");
        System.out.println(JSON.toJSONString(params));
    }

    @JSONField(name = "sym_id")
    public String getSymbolId() {
        return symbolId;
    }

    @JSONField(name = "address")
    public String getPublicKey() {
        return publicKey.toString();
    }

    @Override
    public String asBody() {
        return JSON.toJSONString(this);
    }
}
