package io.everitoken.sdk.java.abi;

import com.alibaba.fastjson.annotation.JSONField;
import io.everitoken.sdk.java.PublicKey;
import io.everitoken.sdk.java.dto.PushableAction;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.stream.Collectors;

public class IssueTokenAction extends Abi implements PushableAction {

    @JSONField(deserialize = false, serialize = false)
    private static final String key = ".issue";

    @JSONField(deserialize = false, serialize = false)
    private static final String name = "issuetoken";

    private final List<String> names;
    private final List<PublicKey> owner;

    private IssueTokenAction(String domain, List<String> names, List<PublicKey> owner) {
        super(name, key, domain);
        this.names = names;
        this.owner = owner;
    }

    @NotNull
    @Contract("_, _, _ -> new")
    public static IssueTokenAction of(String domain, List<String> names, List<PublicKey> owner) {
        return new IssueTokenAction(domain, names, owner);
    }

    @Override
    @JSONField(name = "domain")
    public String getDomain() {
        return super.getDomain();
    }

    public List<String> getNames() {
        return names;
    }

    public List<String> getOwner() {
        return owner.stream().map(PublicKey::toString).collect(Collectors.toList());
    }
}
