package io.everitoken.sdk.java;

import io.everitoken.sdk.java.exceptions.InvalidPublicKeyException;
import io.everitoken.sdk.java.exceptions.WifFormatException;
import org.bitcoinj.core.*;
import org.bitcoinj.params.MainNetParams;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.math.BigInteger;
import java.security.SecureRandom;

public class PrivateKey {
    private final ECKey key;

    private PrivateKey(@NotNull String wif) throws WifFormatException {
        NetworkParameters networkParam = MainNetParams.get();

        if (wif.length() != 51 && wif.length() != 52) {
            throw new WifFormatException("Wif length must be 51 or 52");
        }

        try {
            DumpedPrivateKey dumpedPrivateKey = DumpedPrivateKey.fromBase58(networkParam, wif);
            key = dumpedPrivateKey.getKey();
        } catch (AddressFormatException ex) {
            throw new WifFormatException(ex.getMessage(), ex);
        }
    }

    private PrivateKey(ECKey key) {
        this.key = key;
    }

    @NotNull
    public static PrivateKey randomPrivateKey() {
        SecureRandom sr = new SecureRandom();
        ECKey key = new ECKey(sr);
        return new PrivateKey(key);
    }

    @NotNull
    public static PrivateKey seedPrivateKey(@NotNull String seed) {
        byte[] hash = Sha256Hash.hash(seed.getBytes());
        ECKey key = ECKey.fromPrivate(hash);
        return new PrivateKey(key);
    }

    @NotNull
    @Contract("_ -> new")
    public static PrivateKey of(String wif) throws WifFormatException {
        return new PrivateKey(wif);
    }

    public static boolean isValidPrivateKey(String key) {
        try {
            of(key);
            return true;
        } catch (WifFormatException ex) {
            return false;
        }
    }

    public BigInteger getD() {
        return key.getPrivKey();
    }

    public PublicKey toPublicKey() throws InvalidPublicKeyException {
        return new PublicKey(key.getPubKey());
    }

    public String toWif() {
        ECKey decompressedKey = key.decompress();
        NetworkParameters networkParameters = MainNetParams.get();
        return decompressedKey.getPrivateKeyAsWiF(networkParameters);
    }

}
