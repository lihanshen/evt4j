package io.everitoken.sdk.java.abi;

import com.alibaba.fastjson.annotation.JSONField;
import io.everitoken.sdk.java.Symbol;
import io.everitoken.sdk.java.dto.Permission;
import io.everitoken.sdk.java.dto.PushableAction;
import io.everitoken.sdk.java.param.NetParams;
import io.everitoken.sdk.java.param.TestNetNetParams;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.json.JSONObject;

public class UpdateFungibleAction extends Abi implements PushableAction {
    @JSONField(deserialize = false, serialize = false)
    private static final String domain = ".fungible";
    @JSONField(deserialize = false, serialize = false)
    private static final String name = "updfungible";

    private final Permission issue;
    private final Permission manage;
    private final int symbolName;

    private UpdateFungibleAction(@NotNull Symbol symbol, @Nullable Permission issue, @Nullable Permission manage) {
        super(name, Integer.toString(symbol.getId()), domain);
        symbolName = symbol.getId();
        this.issue = issue;
        this.manage = manage;
    }

    public static void main(String[] args) {
        NetParams netParams = new TestNetNetParams();
        RemoteAbiSerialisationProvider provider = new RemoteAbiSerialisationProvider(netParams);

        UpdateFungibleAction newFungibleAction = UpdateFungibleAction.of(
                Symbol.of(20, 5),
                new JSONObject("{\"name\":\"issue\",\"threshold\":1,\"authorizers\":[{\"ref\":\"[A] " +
                                       "EVT6Qz3wuRjyN6gaU3P3XRxpnEZnM4oPxortemaWDwFRvsv2FxgND\",\"weight\":" +
                                       "1}, {\"ref\":\"[A] " +
                                       "EVT8aNw4NTvjBL1XR6hgy4zcA9jzh1JLjMuAw85mSbW68vYzw2f9H\",\"weight\":" +
                                       "1}]}"),
                null
        );
        System.out.println(newFungibleAction.serialize(provider));
    }

    @Contract("_, _, _ -> new")
    @NotNull
    public static UpdateFungibleAction of(Symbol symbol, @Nullable JSONObject issue, @Nullable JSONObject manage) {
        return new UpdateFungibleAction(symbol, issue != null ? Permission.ofRaw(issue) : null,
                                        manage != null ? Permission.ofRaw(manage) : null
        );
    }

    public Permission getIssue() {
        return issue;
    }

    public Permission getManage() {
        return manage;
    }

    @JSONField(name = "sym_id")
    public int getSymbolName() {
        return symbolName;
    }
}
