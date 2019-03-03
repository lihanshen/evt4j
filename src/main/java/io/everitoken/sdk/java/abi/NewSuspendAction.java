package io.everitoken.sdk.java.abi;

import com.alibaba.fastjson.annotation.JSONField;
import io.everitoken.sdk.java.PublicKey;
import io.everitoken.sdk.java.dto.PushableAction;
import io.everitoken.sdk.java.dto.Transaction;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

public class NewSuspendAction extends Abi implements PushableAction {

    @JSONField(deserialize = false, serialize = false)
    private static final String domain = ".suspend";
    private static final String name = "newsuspend";

    private final PublicKey proposer;
    private final Transaction trx;

    private NewSuspendAction(String proposalName, PublicKey proposer, Transaction trx) {
        super(name, proposalName, domain);
        this.proposer = proposer;
        this.trx = trx;
    }

    @NotNull
    @Contract("_, _, _ -> new")
    public static NewSuspendAction of(String proposalName, String proposer, Transaction tx) {
        return new NewSuspendAction(proposalName, PublicKey.of(proposer), tx);
    }

    public Transaction getTrx() {
        return trx;
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

    public String getProposer() {
        return proposer.toString();
    }
}
