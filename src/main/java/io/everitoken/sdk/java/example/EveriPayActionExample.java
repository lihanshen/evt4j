package io.everitoken.sdk.java.example;

import io.everitoken.sdk.java.EvtLink;
import io.everitoken.sdk.java.PublicKey;
import io.everitoken.sdk.java.abi.EveriPayAction;
import io.everitoken.sdk.java.dto.TransactionData;
import io.everitoken.sdk.java.exceptions.ApiResponseException;
import io.everitoken.sdk.java.param.NetParams;
import io.everitoken.sdk.java.param.TestNetNetParams;
import io.everitoken.sdk.java.provider.KeyProvider;
import io.everitoken.sdk.java.provider.SignProvider;
import io.everitoken.sdk.java.service.TransactionConfiguration;
import io.everitoken.sdk.java.service.TransactionService;

import java.util.Arrays;

public class EveriPayActionExample {
    public static void main(String[] args) {

        int maxAmount = 100;
        NetParams netParams = new TestNetNetParams();
        EvtLink evtLink = new EvtLink(netParams);
        EvtLink.EveriPayParam everiPayParam1 = new EvtLink.EveriPayParam(1, EvtLink.getUniqueLinkId(),
                                                                         maxAmount
        );
        String payText = evtLink.getEveriPayText(
                everiPayParam1,
                SignProvider.of(KeyProvider.of("5J1by7KRQujRdXrurEsvEr2zQGcdPaMJRjewER6XsAR2eCcpt3D"))
        );

        EveriPayAction action = EveriPayAction.of(payText, "0.00001 " +
                "S#1", "EVT5cd4a3RyaVoubc4w3j3Z3YvCJgtKZPRdJHDdk7wVsMbc3yEH5U");

        try {
            TransactionService transactionService = TransactionService.of(netParams);
            TransactionConfiguration trxConfig = new TransactionConfiguration(
                    1000000,
                    PublicKey.of("EVT6Qz3wuRjyN6gaU3P3XRxpnEZnM4oPxortemaWDwFRvsv2FxgND"),
                    KeyProvider.of("5J1by7KRQujRdXrurEsvEr2zQGcdPaMJRjewER6XsAR2eCcpt3D"),
                    null
            );

            TransactionData txData = transactionService.push(trxConfig, Arrays.asList(action));
            System.out.println(txData.getTrxId());
        } catch (ApiResponseException ex) {
            System.out.println(ex.getCause().getMessage());
        }
    }
}
