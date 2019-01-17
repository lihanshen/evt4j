package io.everitoken.sdk.java;

import io.everitoken.sdk.java.exceptions.PublicKeyRecoverFailureException;
import io.everitoken.sdk.java.exceptions.RecoverIDNotFoundException;
import org.bitcoinj.core.ECKey;
import org.bitcoinj.core.Sha256Hash;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.spongycastle.crypto.digests.SHA256Digest;
import org.spongycastle.crypto.params.ECPrivateKeyParameters;
import org.spongycastle.crypto.params.ECPublicKeyParameters;
import org.spongycastle.crypto.signers.ECDSASigner;
import org.spongycastle.crypto.signers.HMacDSAKCalculator;

import java.math.BigInteger;

public class Signature {
    private ECKey.ECDSASignature signature;
    private Integer recId;

    public Signature(BigInteger r, BigInteger s) {
        signature = new ECKey.ECDSASignature(r, s);
    }

    public static Signature signHash(byte[] hash, @NotNull PrivateKey key) {
        // init deterministic k calculator
        ECDSASigner signer = new ECDSASigner(new HMacDSAKCalculator(new SHA256Digest()));
        ECPrivateKeyParameters privKey = new ECPrivateKeyParameters(key.getD(), ECKey.CURVE);

        signer.init(true, privKey);
        BigInteger[] components = signer.generateSignature(hash);
        Signature sig = new Signature(components[0], components[1]).toCanonicalised();

        // find the recId and store in signature for public key recover later
        PublicKey publicKey = key.toPublicKey();
        int recId = getRecId(sig, hash, publicKey);

        if (recId == -1) {
            throw new RecoverIDNotFoundException();
        }

        sig.setRecId(recId);

        return sig;
    }

    /**
     * Sign 32 bits hash with private key and store the recId into signature
     *
     * @param data Data hash with 32 bits
     * @param key  PrivateKey
     * @return Signature
     */
    public static Signature sign(byte[] data, @NotNull PrivateKey key) {
        return signHash(Sha256Hash.hashTwice(data), key);
    }

    /**
     * Calculate the recover id from signature with original data bytes and reference public key
     */
    private static int getRecId(Signature signature, byte[] hash, @NotNull PublicKey publicKey) {
        Sha256Hash dataHash = Sha256Hash.wrap(hash);

        String refPubKey = publicKey.getEncoded(true);

        int recId = -1;
        for (int i = 0; i < 4; i++) {
            ECKey k = ECKey.recoverFromSignature(i, signature.get(), dataHash, true);
            try {
                if (k != null && Utils.HEX.encode(k.getPubKey()).equals(refPubKey)) {
                    return i;
                }
            } catch (Exception ex) {
                // no need to handle anything here
            }
        }

        return recId;
    }

    /**
     * Verify hash message with signature and public key
     *
     * @param data      Data hash with 32 bits
     * @param signature Signature object
     * @param publicKey PublicKey object
     * @return boolean
     */
    public static boolean verify(byte[] data, @NotNull Signature signature, @NotNull PublicKey publicKey) {
        return verifyHash(Sha256Hash.hashTwice(data), signature, publicKey);
    }

    public static boolean verifyHash(byte[] hash, @NotNull Signature signature, @NotNull PublicKey publicKey) {
        ECDSASigner signer = new ECDSASigner(new HMacDSAKCalculator(new SHA256Digest()));

        ECPublicKeyParameters publicKeyParams = new ECPublicKeyParameters(
                publicKey.getPoint().get(), // ECPoint
                ECKey.CURVE
        );

        signer.init(false, publicKeyParams);

        return signer.verifySignature(hash, signature.getR(), signature.getS());
    }

    /**
     * Recover public key from signature and original data byte[].
     * Note: one always need to compare the public key recovered from signature match with whe reference public key
     *
     * @param data      original data signed by the private key
     * @param signature signature from sign method
     * @return
     */
    @NotNull
    @Contract("_, _ -> new")
    public static PublicKey recoverPublicKey(byte[] data, @NotNull Signature signature) {
        Sha256Hash dataHash = Sha256Hash.wrap(data);

        ECKey k = ECKey.recoverFromSignature(signature.getRecId(), signature.get(), dataHash, true);
        if (k == null) {
            throw new PublicKeyRecoverFailureException();
        }

        return new PublicKey(k.getPubKey());
    }

    @Contract(pure = true)
    private ECKey.ECDSASignature get() {
        return signature;
    }

    public BigInteger getR() {
        return signature.r;
    }

    public BigInteger getS() {
        return signature.s;
    }

    public Integer getRecId() {
        return recId;
    }

    private void setRecId(int id) {
        recId = id;
    }

    public boolean isCanonical() {
        return signature.isCanonical();
    }

    @Contract(" -> this")
    private Signature toCanonicalised() {
        signature = signature.toCanonicalised();
        return this;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }

        if (!(obj instanceof Signature)) {
            return false;
        }
        Signature s = (Signature) obj;

        return getR().equals(s.getR()) && getS().equals(s.getS()) && getRecId().equals(s.getRecId());
    }
}
