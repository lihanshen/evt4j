package io.everitoken.sdk.java;

import io.everitoken.sdk.java.exceptions.Base58CheckException;
import io.everitoken.sdk.java.exceptions.InvalidPublicKeyException;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;
import org.bitcoinj.core.ECKey;
import org.bitcoinj.crypto.LazyECPoint;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

public class PublicKey {
    private static final String EVT = "EVT";
    private final LazyECPoint pub;

    public PublicKey(String key) throws InvalidPublicKeyException {
        Pair<Boolean, byte[]> pair = validPublicKey(key);

        if (!pair.getLeft()) {
            throw new InvalidPublicKeyException(key);
        }

        pub = new LazyECPoint(ECKey.CURVE.getCurve(), pair.getRight());
    }

    public PublicKey(byte[] key) throws InvalidPublicKeyException {
        LazyECPoint point = new LazyECPoint(ECKey.CURVE.getCurve(), key);

        if (!point.isValid()) {
            throw new InvalidPublicKeyException();
        }

        pub = point;
    }


    public static boolean isValidPublicKey(String key) {
        return validPublicKey(key).getLeft();
    }

    @NotNull
    @Contract("_ -> new")
    private static Pair<Boolean, byte[]> validPublicKey(@NotNull String key) {
        if (key.length() < 8) {
            return new ImmutablePair<>(false, new byte[]{});
        }

        if (!key.startsWith(EVT)) {
            return new ImmutablePair<>(false, new byte[]{});
        }

        // key is invalid when checksum doesn't match
        String keyWithoutPrefix = key.substring(3);
        byte[] publicKeyInBytes;
        try {
            publicKeyInBytes = Utils.base58CheckDecode(keyWithoutPrefix);
        } catch (Base58CheckException ex) {
            return new ImmutablePair<>(false, new byte[]{});
        }

        LazyECPoint pub = new LazyECPoint(ECKey.CURVE.getCurve(), publicKeyInBytes);

        return new ImmutablePair<>(pub.isValid(), publicKeyInBytes);
    }

    @NotNull
    @Contract("_ -> new")
    public static PublicKey of(String key) {
        return new PublicKey(key);
    }

    public LazyECPoint getPoint() {
        return pub;
    }

    @Override
    public String toString() {
        return String.format("%s%s", EVT, Utils.base58Check(pub.getEncoded(true)));
    }

    public String getEncoded(boolean compressed) {
        return Utils.HEX.encode(pub.getEncoded(compressed));
    }
}
