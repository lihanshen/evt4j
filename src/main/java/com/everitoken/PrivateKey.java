package com.everitoken;

import org.apache.commons.lang3.ArrayUtils;
import org.bitcoinj.core.*;
import org.bitcoinj.params.MainNetParams;
import org.spongycastle.crypto.digests.RIPEMD160Digest;
import org.spongycastle.crypto.util.PublicKeyFactory;
import sun.applet.Main;

import java.math.BigInteger;
import java.nio.ByteBuffer;
import java.security.PublicKey;
import java.security.SecureRandom;

public class PrivateKey {
    private static final String nullAddress = "EVT00000000000000000000000000000000000000000000000000";
    private ECKey key;

    public PrivateKey(ECKey key) {
        this.key = key;
    }

    public String toPublicKey() {
        BigInteger prvKey = this.key.getPrivKey();
        byte[] pubKey = ECKey.publicKeyFromPrivate(prvKey, true);

        // TODO refactor out
        RIPEMD160Digest digest = new RIPEMD160Digest();
        digest.update(pubKey,0, pubKey.length);
        byte[] out = new byte[20];
        digest.doFinal(out, 0);
        byte[] concat = ArrayUtils.addAll(pubKey, ArrayUtils.subarray(out, 0,4));

        return "EVT" + Base58.encode(concat);
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
