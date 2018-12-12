package io.everitoken.sdk.java;

import org.bitcoinj.core.ECKey;

import java.math.BigInteger;

public class Signature {
    private ECKey.ECDSASignature signature;

    public Signature(BigInteger r, BigInteger s) {
        signature = new ECKey.ECDSASignature(r, s);
    }

    public BigInteger getR() {
        return signature.r;
    }

    public BigInteger getS() {
        return signature.s;
    }
}
