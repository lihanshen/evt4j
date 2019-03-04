package io.everitoken.sdk.java.example;

import io.everitoken.sdk.java.PublicKey;
import io.everitoken.sdk.java.abi.Evt2PevtAction;
import io.everitoken.sdk.java.abi.NewSuspendAction;
import io.everitoken.sdk.java.dto.Transaction;
import io.everitoken.sdk.java.dto.TransactionData;
import io.everitoken.sdk.java.exceptions.ApiResponseException;
import io.everitoken.sdk.java.param.NetParams;
import io.everitoken.sdk.java.param.TestNetNetParams;
import io.everitoken.sdk.java.provider.KeyProvider;
import io.everitoken.sdk.java.service.TransactionConfiguration;
import io.everitoken.sdk.java.service.TransactionService;

import java.util.Arrays;

public class NewSuspendExample {
    public static void main(String[] args) {
        NetParams netParam = new TestNetNetParams();
        Evt2PevtAction evt2PevtAction = Evt2PevtAction.of(
                "0.30000 S#1",
                "EVT6Qz3wuRjyN6gaU3P3XRxpnEZnM4oPxortemaWDwFRvsv2FxgND",
                "EVT8aNw4NTvjBL1XR6hgy4zcA9jzh1JLjMuAw85mSbW68vYzw2f9H",
                "test java"
        );

        TransactionService transactionService = TransactionService.of(netParam);
        TransactionConfiguration txConfig = new TransactionConfiguration(
                1000000,
                PublicKey.of("EVT6Qz3wuRjyN6gaU3P3XRxpnEZnM4oPxortemaWDwFRvsv2FxgND"),
                KeyProvider.of("5J1by7KRQujRdXrurEsvEr2zQGcdPaMJRjewER6XsAR2eCcpt3D")
        );
        try {
            Transaction tx = transactionService.buildRawTransaction(txConfig, Arrays.asList(evt2PevtAction));
            NewSuspendAction action = NewSuspendAction.of("testProposal8",
                                                          "EVT6Qz3wuRjyN6gaU3P3XRxpnEZnM4oPxortemaWDwFRvsv2FxgND", tx
            );

            TransactionData push = transactionService.push(txConfig, Arrays.asList(action));
            System.out.println(push.getTrxId());
        } catch (ApiResponseException ex) {
            System.out.println(ex.getCause().getMessage());
        }
    }
}
