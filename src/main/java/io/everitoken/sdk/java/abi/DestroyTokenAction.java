package io.everitoken.sdk.java.abi;

import com.alibaba.fastjson.annotation.JSONField;
import io.everitoken.sdk.java.dto.PushableAction;
import io.everitoken.sdk.java.param.NetParams;
import io.everitoken.sdk.java.param.TestNetNetParams;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

public class DestroyTokenAction extends Abi implements PushableAction {

    @JSONField(deserialize = false, serialize = false)
    private static final String name = "destroytoken";

    private DestroyTokenAction(String domain, String tokenName) {
        super(name, tokenName, domain);
    }

    public static void main(String[] args) {
        NetParams netParams = new TestNetNetParams();
        RemoteAbiSerialisationProvider provider = new RemoteAbiSerialisationProvider(netParams);

        DestroyTokenAction destroyTokenAction = DestroyTokenAction.of(
                "test1119",
                "t3"
        );
        System.out.println(destroyTokenAction.serialize(provider));
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
