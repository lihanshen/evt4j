package io.everitoken.sdk.java.abi;

import com.alibaba.fastjson.annotation.JSONField;
import io.everitoken.sdk.java.PublicKey;
import io.everitoken.sdk.java.dto.Permission;
import io.everitoken.sdk.java.dto.PushableAction;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.json.JSONObject;

import java.util.Objects;

public class NewDomainAction extends Abi implements PushableAction {
    @JSONField(deserialize = false, serialize = false)
    private static final String key = ".create";
    @JSONField(deserialize = false, serialize = false)
    private static final String name = "newdomain";
    private final PublicKey creator;
    private final Permission issue;
    private final Permission transfer;
    private final Permission manage;

    protected NewDomainAction(String domain, PublicKey creator, Permission issue, Permission transfer,
                              Permission manage) {
        super(name, key, domain);
        this.creator = creator;
        this.issue = issue;
        this.transfer = transfer;
        this.manage = manage;
    }

    protected NewDomainAction(String domain, String creator, JSONObject issue, JSONObject transfer, JSONObject manage) {
        this(domain, PublicKey.of(creator), Permission.ofRaw(issue), Permission.ofRaw(transfer),
             Permission.ofRaw(manage)
        );
    }

    @NotNull
    @Contract("_, _, _, _, _ -> new")
    public static NewDomainAction ofRaw(String domain, String creator, JSONObject issue, JSONObject transfer,
                                        JSONObject manage) {
        Objects.requireNonNull(issue);
        Objects.requireNonNull(transfer);
        Objects.requireNonNull(manage);

        return new NewDomainAction(domain, creator, issue, transfer, manage);
    }

    @JSONField(ordinal = 1)
    public String getCreator() {
        return creator.toString();
    }

    @JSONField(ordinal = 2)
    public Permission getIssue() {
        return issue;
    }

    @JSONField(ordinal = 3)
    public Permission getTransfer() {
        return transfer;
    }

    @JSONField(ordinal = 4)
    public Permission getManage() {
        return manage;
    }
}
