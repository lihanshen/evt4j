package io.everitoken.sdk.java.abi;

import com.alibaba.fastjson.annotation.JSONField;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import io.everitoken.sdk.java.Address;
import io.everitoken.sdk.java.Asset;

public class TransferFungibleAction extends Abi {

    @JSONField(deserialize = false, serialize = false)
    private static final String domain = ".fungible";

    @JSONField(deserialize = false, serialize = false)
    private static final String name = "transferft";

    private final Asset asset;
    private final String memo;
    private final Address from;
    private final Address to;

    private TransferFungibleAction(@NotNull Asset asset, Address from, Address to, String memo) {
        super(name, Integer.toString(asset.getSymbol().getId()), domain);
        this.asset = asset;
        this.from = from;
        this.to = to;
        this.memo = memo;
    }

    @Contract("_, _, _, _ -> new")
    @NotNull
    public static TransferFungibleAction of(String balance, String from, String to, String memo) {
        return new TransferFungibleAction(Asset.parseFromRawBalance(balance), Address.of(from), Address.of(to), memo);
    }

    @JSONField(name = "number")
    public String getAsset() {
        return asset.toString();
    }

    public String getMemo() {
        return memo;
    }

    public String getFrom() {
        return from.toString();
    }

    public String getTo() {
        return to.toString();
    }
}
