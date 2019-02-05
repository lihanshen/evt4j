package io.everitoken.sdk.java.apiResource;

import io.everitoken.sdk.java.PublicKey;

import java.util.ArrayList;
import java.util.List;

class TransctionConfiguration {
    private final List<PublicKey> availablePublicKeys;
    private final PublicKey payer;
    private int maxCharge = 100000000;
    private boolean estimateCharge = false;

    private TransctionConfiguration(int maxCharge, boolean estimateCharge, List<PublicKey> availablePublicKeys,
                                    PublicKey payer) {
        this.maxCharge = maxCharge;
        this.estimateCharge = estimateCharge;
        this.availablePublicKeys = availablePublicKeys;
        this.payer = payer;
    }

    public static TransctionConfiguration defaultConfig(int maxCharge, PublicKey payer) {
        return new TransctionConfiguration(maxCharge, false, new ArrayList<>(), payer);
    }
}

