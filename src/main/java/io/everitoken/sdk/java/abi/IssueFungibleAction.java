package io.everitoken.sdk.java.abi;

import com.alibaba.fastjson.annotation.JSONField;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import io.everitoken.sdk.java.Address;
import io.everitoken.sdk.java.Asset;

public class IssueFungibleAction extends Abi {

    @JSONField(deserialize = false, serialize = false)
    private static final String domain = ".fungible";

    @JSONField(deserialize = false, serialize = false)
    private static final String name = "issuefungible";

    private final Asset asset;
    private final String memo;
    private final Address address;

    private IssueFungibleAction(@NotNull Asset asset, Address address, String memo) {
        super(name, Integer.toString(asset.getSymbol().getId()), domain);
        this.memo = memo;
        this.asset = asset;
        this.address = address;
    }

    @NotNull
    @Contract("_, _, _ -> new")
    public static IssueFungibleAction of(String balance, String address, String memo) {
        return new IssueFungibleAction(Asset.parseFromRawBalance(balance), Address.of(address), memo);
    }

    @JSONField(name = "number")
    public String getAsset() {
        return asset.toString();
    }

    public String getMemo() {
        return memo;
    }

    public String getAddress() {
        return address.toString();
    }
}
