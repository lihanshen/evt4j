package io.everitoken.sdk.java;

import org.bitcoinj.core.*;
import org.bitcoinj.params.MainNetParams;
import org.spongycastle.crypto.digests.SHA256Digest;
import org.spongycastle.crypto.params.ECPrivateKeyParameters;
import org.spongycastle.crypto.signers.ECDSASigner;
import org.spongycastle.crypto.signers.HMacDSAKCalculator;

import java.math.BigInteger;
import java.security.SecureRandom;

public class PrivateKey {
    private ECKey key;

    public PrivateKey(String wif) throws IllegalArgumentException {
        NetworkParameters networkParam = MainNetParams.get();

        if (wif.length() != 51 && wif.length() != 52) {
            throw new IllegalArgumentException("Wif length must be 51 or 52");
        }

        DumpedPrivateKey dumpedPrivateKey = DumpedPrivateKey.fromBase58(networkParam, wif);
        this.key = dumpedPrivateKey.getKey();
    }

    private PrivateKey(ECKey key) {
        this.key = key;
    }

    public String toPublicKey() {
        BigInteger prvKey = this.key.getPrivKey();
        byte[] pubKey = ECKey.publicKeyFromPrivate(prvKey, true);

        // TODO return public key class instead
        return Constants.EVT + Utils.base58Check(pubKey);
    }

    private byte[] getPrivKeyBytes() {
        return key.getPrivKeyBytes();
    }

    public String toWif() {
        ECKey decompressedKey = this.key.decompress();
        NetworkParameters networkParameters = MainNetParams.get();
        return decompressedKey.getPrivateKeyAsWiF(networkParameters);
    }

    public Signature sign(String data) {
        byte[] hash = Sha256Hash.hash(data.getBytes());
        return signHash(Utils.HEX.encode(hash));
    }

    public Signature signHash(String hash) {
        BigInteger privateKeyForSigning = new BigInteger(1, this.getPrivKeyBytes());
        ECDSASigner signer = new ECDSASigner(new HMacDSAKCalculator(new SHA256Digest()));
        ECPrivateKeyParameters privKey = new ECPrivateKeyParameters(privateKeyForSigning, ECKey.CURVE);
        signer.init(true, privKey);
        BigInteger[] signature = signer.generateSignature(Utils.HEX.decode(hash));

        return new Signature(signature[0], signature[1]);
    }

    public static PrivateKey randomPrivateKey() {
        SecureRandom sr = new SecureRandom();
        ECKey key = new ECKey(sr);
        return new PrivateKey(key);
    }

    public static PrivateKey seedPrivateKey(String seed) {
        byte[] hash = Sha256Hash.hash(seed.getBytes());
        ECKey key = ECKey.fromPrivate(hash);
        return new PrivateKey(key);
    }

    public static PrivateKey fromWif(String wif) throws EvtSdkException {
        try {
            return new PrivateKey(wif);
        } catch (IllegalArgumentException ex) {
            throw new EvtSdkException(ex, ErrorCode.WIF_FORMAT_INVALID);
        }
    }

    public static boolean isValidPrivateKey(String key) {
        try {
            fromWif(key);
            return true;
        } catch (EvtSdkException ex) {
            return false;
        }
    }

}
