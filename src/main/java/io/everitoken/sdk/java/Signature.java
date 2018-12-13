package io.everitoken.sdk.java;

import org.bitcoinj.core.ECKey;
import org.spongycastle.crypto.digests.SHA256Digest;
import org.spongycastle.crypto.params.ECPrivateKeyParameters;
import org.spongycastle.crypto.params.ECPublicKeyParameters;
import org.spongycastle.crypto.signers.ECDSASigner;
import org.spongycastle.crypto.signers.HMacDSAKCalculator;

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

    public byte[] encodeToDER() {
        return signature.encodeToDER();
    }

    public boolean isCanonical() {
        return signature.isCanonical();
    }

    public Signature toCanonicalised() {
        signature = signature.toCanonicalised();
        return this;
    }

    public static Signature signHash(byte[] hash, PrivateKey key) {
        ECDSASigner signer = new ECDSASigner(new HMacDSAKCalculator(new SHA256Digest()));
        ECPrivateKeyParameters privKey = new ECPrivateKeyParameters(key.getD(), ECKey.CURVE);
        signer.init(true, privKey);
        BigInteger[] components = signer.generateSignature(hash);
        return new Signature(components[0], components[1]).toCanonicalised();
    }

    public static boolean verify(byte[] data, Signature signature, PublicKey publicKey) {
        ECDSASigner signer = new ECDSASigner(new HMacDSAKCalculator(new SHA256Digest()));

        ECPublicKeyParameters publicKeyParams = new ECPublicKeyParameters(
                publicKey.get().get(),
                ECKey.CURVE
        );

        signer.init(false, publicKeyParams);

        return signer.verifySignature(data, signature.getR(), signature.getS());
    }
}
