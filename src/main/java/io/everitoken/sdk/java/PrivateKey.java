package io.everitoken.sdk.java;

import org.bitcoinj.core.DumpedPrivateKey;
import org.bitcoinj.core.ECKey;
import org.bitcoinj.core.NetworkParameters;
import org.bitcoinj.core.Sha256Hash;
import org.bitcoinj.params.MainNetParams;

import java.math.BigInteger;
import java.security.SecureRandom;

public class PrivateKey {
    private ECKey key;

    private PrivateKey(String wif) throws IllegalArgumentException {
        NetworkParameters networkParam = MainNetParams.get();

        if (wif.length() != 51 && wif.length() != 52) {
            throw new IllegalArgumentException("Wif length must be 51 or 52");
        }

        DumpedPrivateKey dumpedPrivateKey = DumpedPrivateKey.fromBase58(networkParam, wif);
        key = dumpedPrivateKey.getKey();
    }

    private PrivateKey(ECKey key) {
        this.key = key;
    }

    public BigInteger getD() {
        return key.getPrivKey();
    }

    public PublicKey toPublicKey() throws EvtSdkException {
        return new PublicKey(key.getPubKey());
    }

    public String toWif() {
        ECKey decompressedKey = key.decompress();
        NetworkParameters networkParameters = MainNetParams.get();
        return decompressedKey.getPrivateKeyAsWiF(networkParameters);
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
