package io.everitoken.sdk.java.dto;

// {
//     body: {
//         transaction: {
//             actions: [action],
//             expiration: "",
//             ref_block_num: "",
//             ref_block_prefix: "",
//             max_charge: "",
//             payer: "",
//         },
//         compression: "none",
//     }
// }

import com.alibaba.fastjson.annotation.JSONField;

import java.util.List;

public class Transaction {
    private final List<String> actions;
    private final String expiration;
    private final short refBlockNumber;
    private final long refBlockPrefix;
    private final int maxCharge;
    private final String payer;

    public Transaction(List<String> actions, String expiration, short refBlockNumber, long refBlockPrefix,
                       int maxCharge
            , String payer) {
        this.actions = actions;
        this.expiration = expiration;
        this.refBlockNumber = refBlockNumber;
        this.refBlockPrefix = refBlockPrefix;
        this.maxCharge = maxCharge;
        this.payer = payer;
    }

    public List<String> getActions() {
        return actions;
    }

    public String getExpiration() {
        return expiration;
    }

    @JSONField(name = "ref_block_num")
    public short getRefBlockNumber() {
        return refBlockNumber;
    }

    @JSONField(name = "ref_block_prefix")
    public long getRefBlockPrefix() {
        return refBlockPrefix;
    }

    @JSONField(name = "max_charge")
    public int getMaxCharge() {
        return maxCharge;
    }

    public String getPayer() {
        return payer;
    }
}
