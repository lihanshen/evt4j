package io.everitoken.sdk.java.abi;

import com.alibaba.fastjson.annotation.JSONField;
import io.everitoken.sdk.java.PublicKey;
import io.everitoken.sdk.java.Signature;
import io.everitoken.sdk.java.dto.PushableAction;
import io.everitoken.sdk.java.dto.TransactionData;
import io.everitoken.sdk.java.param.NetParams;
import io.everitoken.sdk.java.param.TestNetNetParams;
import io.everitoken.sdk.java.provider.KeyProvider;
import io.everitoken.sdk.java.service.TransactionConfiguration;
import io.everitoken.sdk.java.service.TransactionService;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class ApproveSuspendAction extends Abi implements PushableAction {

    @JSONField(deserialize = false, serialize = false)
    private static final String domain = ".suspend";
    private static final String name = "aprvsuspend";

    private final List<Signature> signatures;

    private ApproveSuspendAction(String proposalName, List<Signature> signatures) {
        super(name, proposalName, domain);
        this.signatures = signatures;
    }

    public static void main(String[] args) {
        NetParams netParam = new TestNetNetParams();
        TransactionService transactionService = TransactionService.of(netParam);
        KeyProvider keyProvider = KeyProvider.of("5J1by7KRQujRdXrurEsvEr2zQGcdPaMJRjewER6XsAR2eCcpt3D");

        try {
            List<Signature> signatures = transactionService.getSignaturesByProposalName(keyProvider, "testProposal10");
            signatures.stream().forEach(signature -> System.out.println(signature.toString()));

            ApproveSuspendAction action = ApproveSuspendAction.of(
                    "testProposal10",
                    signatures.stream().map(Signature::toString).collect(Collectors.toList())
            );
            TransactionConfiguration txConfig = new TransactionConfiguration(
                    1000000,
                    PublicKey.of("EVT6Qz3wuRjyN6gaU3P3XRxpnEZnM4oPxortemaWDwFRvsv2FxgND"),
                    keyProvider
            );

            TransactionData txData = transactionService.push(txConfig, Arrays.asList(action));
            System.out.println(txData.getTrxId());
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    @NotNull
    @Contract("_, _ -> new")
    public static ApproveSuspendAction of(String proposalName, List<String> signatures) {
        return new ApproveSuspendAction(
                proposalName,
                signatures.stream().map(Signature::of).collect(Collectors.toList())
        );
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
