package io.everitoken.sdk.java.service;

import io.everitoken.sdk.java.PublicKey;
import io.everitoken.sdk.java.provider.KeyProviderInterface;
import io.everitoken.sdk.java.provider.SignProvider;
import io.everitoken.sdk.java.provider.SignProviderInterface;

public class TransactionConfiguration {
    private final int maxCharge;
    private final PublicKey payer;
    private final SignProviderInterface signProvider;

    public TransactionConfiguration(final int maxCharge, final PublicKey payer,
                                    SignProviderInterface signProvider) {
        this.maxCharge = maxCharge;
        this.payer = payer;
        this.signProvider = signProvider;
    }

    public TransactionConfiguration(final int maxCharge, final PublicKey payer, KeyProviderInterface keyProvider) {
        this(maxCharge, payer, SignProvider.of(keyProvider));
    }

    public int getMaxCharge() {
        return maxCharge;
    }

    public String getPayer() {
        return payer.toString();
    }

    public SignProviderInterface getSignProvider() {
        return signProvider;
    }
}

