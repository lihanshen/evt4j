package io.everitoken.sdk.java.abi;

import java.util.List;
import java.util.stream.Collectors;

import com.alibaba.fastjson.annotation.JSONField;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import io.everitoken.sdk.java.Signature;
import io.everitoken.sdk.java.dto.PushableAction;

public class ApproveSuspendAction extends Abi implements PushableAction {

    @JSONField(deserialize = false, serialize = false)
    private static final String domain = ".suspend";
    private static final String name = "aprvsuspend";

    private final List<Signature> signatures;

    private ApproveSuspendAction(String proposalName, List<Signature> signatures) {
        super(name, proposalName, domain);
        this.signatures = signatures;
    }

    @NotNull
    @Contract("_, _ -> new")
    public static ApproveSuspendAction of(String proposalName, List<String> signatures) {
        return new ApproveSuspendAction(proposalName,
                signatures.stream().map(Signature::of).collect(Collectors.toList()));
    }

    @Override
    @JSONField(name = "name")
    public String getKey() {
        return super.getKey();
    }

    @Override
    @JSONField(name = "key")
    public String getName() {
        return super.getName();
    }

    public List<String> getSignatures() {
        return signatures.stream().map(Signature::toString).collect(Collectors.toList());
    }
}
