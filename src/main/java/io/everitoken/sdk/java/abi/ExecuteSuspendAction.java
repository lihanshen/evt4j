package io.everitoken.sdk.java.abi;

import com.alibaba.fastjson.annotation.JSONField;
import io.everitoken.sdk.java.PublicKey;
import io.everitoken.sdk.java.dto.PushableAction;
import org.jetbrains.annotations.NotNull;

public class ExecuteSuspendAction extends Abi implements PushableAction {

    @JSONField(deserialize = false, serialize = false)
    private static final String domain = ".suspend";

    @JSONField(deserialize = false, serialize = false)
    private static final String name = "execsuspend";

    private final PublicKey executor;

    private ExecuteSuspendAction(String proposalName, PublicKey executor) {
        super(name, proposalName, domain);
        this.executor = executor;
    }

    @NotNull
    public static ExecuteSuspendAction of(String proposalName, String executor) {
        return new ExecuteSuspendAction(proposalName, PublicKey.of(executor));
    }

    @Override
    @JSONField(name = "name")
    public String getKey() {
        return super.getKey();
    }

    public String getExecutor() {
        return executor.toString();
    }
}
