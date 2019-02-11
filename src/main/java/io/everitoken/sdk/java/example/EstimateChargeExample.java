package io.everitoken.sdk.java.example;

import com.alibaba.fastjson.JSON;
import io.everitoken.sdk.java.PublicKey;
import io.everitoken.sdk.java.abi.IssueTokenAction;
import io.everitoken.sdk.java.dto.Charge;
import io.everitoken.sdk.java.exceptions.ApiResponseException;
import io.everitoken.sdk.java.param.NetParams;
import io.everitoken.sdk.java.param.TestNetNetParams;
import io.everitoken.sdk.java.provider.KeyProvider;
import io.everitoken.sdk.java.service.TransactionConfiguration;
import io.everitoken.sdk.java.service.TransactionService;

import java.util.Arrays;
import java.util.Collections;

public class EstimateChargeExample {
    public static void main(String[] args) {
        final NetParams netParam = new TestNetNetParams();
        IssueTokenAction issueTokenAction = IssueTokenAction.of(
                "test1119",
                Arrays.asList("t1", "t2"),
                Collections.singletonList(PublicKey.of("EVT6Qz3wuRjyN6gaU3P3XRxpnEZnM4oPxortemaWDwFRvsv2FxgND"))
        );

        try {
            TransactionService transactionService = TransactionService.of(netParam);
            TransactionConfiguration txConfig = new TransactionConfiguration(
                    1000000,
                    PublicKey.of("EVT6Qz3wuRjyN6gaU3P3XRxpnEZnM4oPxortemaWDwFRvsv2FxgND"),
                    KeyProvider.of("5J1by7KRQujRdXrurEsvEr2zQGcdPaMJRjewER6XsAR2eCcpt3D")
            );
            Charge charge = transactionService.estimateCharge(
                    txConfig,
                    Arrays.asList(issueTokenAction),
                    Arrays.asList(PublicKey.of("EVT6Qz3wuRjyN6gaU3P3XRxpnEZnM4oPxortemaWDwFRvsv2FxgND"))
            );
            System.out.println(JSON.toJSONString(charge));
        } catch (final ApiResponseException ex) {
            System.out.println(ex.getRaw());
        }
    }
}
