package io.everitoken.sdk.java.example;

import io.everitoken.sdk.java.PublicKey;
import io.everitoken.sdk.java.Signature;
import io.everitoken.sdk.java.abi.ApproveSuspendAction;
import io.everitoken.sdk.java.dto.TransactionData;
import io.everitoken.sdk.java.param.NetParams;
import io.everitoken.sdk.java.param.TestNetNetParams;
import io.everitoken.sdk.java.provider.KeyProvider;
import io.everitoken.sdk.java.service.TransactionConfiguration;
import io.everitoken.sdk.java.service.TransactionService;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class ApproveSuspendExample {
    public static void main(String[] args) {
        NetParams netParam = new TestNetNetParams();
        TransactionService transactionService = TransactionService.of(netParam);
        KeyProvider keyProvider = KeyProvider.of("5J1by7KRQujRdXrurEsvEr2zQGcdPaMJRjewER6XsAR2eCcpt3D");

        try {
            List<Signature> signatures = transactionService.getSignaturesByProposalName(
                    keyProvider,
                    "testProposal12"
            );

            ApproveSuspendAction action = ApproveSuspendAction.of(
                    "testProposal12",
                    signatures.stream().map(Signature::toString).collect(Collectors.toList())
            );

            TransactionConfiguration trxConfig = new TransactionConfiguration(
                    1000000,
                    PublicKey.of("EVT6Qz3wuRjyN6gaU3P3XRxpnEZnM4oPxortemaWDwFRvsv2FxgND"),
                    keyProvider
            );

            TransactionData txData = transactionService.push(trxConfig, Arrays.asList(action));
            System.out.println(txData.getTrxId());

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
}
