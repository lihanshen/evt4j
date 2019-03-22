package io.everitoken.sdk.java.abi;

import java.util.List;
import java.util.stream.Collectors;

import com.alibaba.fastjson.annotation.JSONField;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import io.everitoken.sdk.java.Address;
import io.everitoken.sdk.java.dto.PushableAction;

public class TransferAction extends Abi implements PushableAction {

    @JSONField(deserialize = false, serialize = false)
    private static final String name = "transfer";

    private final List<Address> to;
    private final String memo;

    private TransferAction(String domain, String tokenName, List<Address> to, String memo) {
        super(name, tokenName, domain);
        this.memo = memo;
        this.to = to;
    }

    @NotNull
    @Contract("_, _, _, _ -> new")
    public static TransferAction of(String domain, String tokenName, List<String> to, String memo) {
        return new TransferAction(domain, tokenName, to.stream().map(Address::of).collect(Collectors.toList()), memo);
    }

    @Override
    @JSONField(name = "name")
    public String getKey() {
        return super.getKey();
    }

    public String getMemo() {
        return memo;
    }

    public List<String> getTo() {
        return to.stream().map(Address::toString).collect(Collectors.toList());
    }
}
