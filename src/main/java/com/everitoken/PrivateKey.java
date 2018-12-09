package com.everitoken;

import org.bitcoinj.core.*;
import org.bitcoinj.params.MainNetParams;

import java.math.BigInteger;
import java.security.SecureRandom;

public class PrivateKey {
    private static final String nullAddress = Constants.NullAddress;
    private ECKey key;

    public PrivateKey(ECKey key) {
        this.key = key;
    }

    public String toPublicKey() {
        BigInteger prvKey = this.key.getPrivKey();
        byte[] pubKey = ECKey.publicKeyFromPrivate(prvKey, true);

        return Constants.EVT + Utils.base58Check(pubKey);
    }

    public String toWif() {
        ECKey decompressedKey = this.key.decompress();
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

    public static PrivateKey fromWif(String wif) {
        NetworkParameters networkParam = MainNetParams.get();

        if (wif.length() != 51 && wif.length() != 52) {
            throw new Error("Wif length must be 51 or 52");
        }

        DumpedPrivateKey dumpedPrivateKey = DumpedPrivateKey.fromBase58(networkParam, wif);
        ECKey key = dumpedPrivateKey.getKey();
        return new PrivateKey(key);
    }

    public static boolean isValidPrivateKey(String key) {
        try {
            fromWif(key);
            return true;
        } catch(Exception ex) {
            return false;
        }
    }

    public static String getNullAddress() {
        return nullAddress;
    }
}
