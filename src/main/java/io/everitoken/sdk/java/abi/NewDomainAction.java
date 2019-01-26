package io.everitoken.sdk.java.abi;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.annotation.JSONField;
import io.everitoken.sdk.java.PublicKey;
import io.everitoken.sdk.java.dto.Permission;
import io.everitoken.sdk.java.dto.TransactionalAction;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.json.JSONObject;

import java.util.Objects;

public class NewDomainAction implements TransactionalAction, AbiImpl {
    @JSONField(deserialize = false, serialize = false)
    private static final String key = ".create";
    @JSONField(deserialize = false, serialize = false)
    private static final String name = "newdomain";
    private final String domain;
    private final PublicKey creator;
    private final Permission issue;
    private final Permission transfer;
    private final Permission manage;

    protected NewDomainAction(String domain, PublicKey creator, Permission issue, Permission transfer,
                              Permission manage) {
        this.domain = domain;
        this.creator = creator;
        this.issue = issue;
        this.transfer = transfer;
        this.manage = manage;
    }

    protected NewDomainAction(String domain, String creator, JSONObject issue, JSONObject transfer, JSONObject manage) {
        this.domain = domain;
        this.creator = PublicKey.of(creator);
        this.issue = Permission.ofRaw(issue);
        this.transfer = Permission.ofRaw(transfer);
        this.manage = Permission.ofRaw(manage);
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

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getKey() {
        return key;
    }

    @Override
    @JSONField(name = "name")
    public String getDomain() {
        return domain;
    }

    @Override
    public String getData(AbiSerialisationProvider provider) {
        return provider.serialize(JSON.toJSONString(new AbiToBin(getName(), this)));
    }

    @Override
    public String serialize(AbiSerialisationProvider provider) {
        JSONObject payload = new JSONObject();
        payload.put("name", getName());
        payload.put("key", getKey());
        payload.put("domain", getDomain());
        payload.put("data", getData(provider));
        return payload.toString();
    }
}

class AbiToBin {
    private final String action;
    private final NewDomainAction args;

    AbiToBin(String action, NewDomainAction args) {
        this.action = action;
        this.args = args;
    }

    public String getAction() {
        return action;
    }

    @JSONField(ordinal = 1)
    public NewDomainAction getArgs() {
        return args;
    }
}
