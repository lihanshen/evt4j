package io.everitoken.sdk.java.abi;

import com.alibaba.fastjson.annotation.JSONField;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

public class DestroyTokenAction extends Abi {

    @JSONField(deserialize = false, serialize = false)
    private static final String name = "destroytoken";

    private DestroyTokenAction(String domain, String tokenName) {
        super(name, tokenName, domain);
    }

    @NotNull
    @Contract("_, _ -> new")
    public static DestroyTokenAction of(String domain, String tokenName) {
        return new DestroyTokenAction(domain, tokenName);
    }

    @Override
    @JSONField(name = "name")
    public String getKey() {
        return super.getKey();
    }
}
