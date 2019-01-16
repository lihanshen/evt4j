package io.everitoken.sdk.java;

import io.everitoken.sdk.java.exceptions.Base58CheckException;
import io.everitoken.sdk.java.exceptions.InvalidPublicKeyException;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;
import org.bitcoinj.core.ECKey;
import org.bitcoinj.crypto.LazyECPoint;

public class PublicKey {
    private static final String nullAddress = Constants.NullAddress;
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

    public static boolean isValidAddress(String key) {
        if (key.equals(Constants.NullAddress)) {
            return true;
        }

        if (key.length() == 53 && key.charAt(3) == '0') {
            return true;
        }

        return isValidPublicKey(key);
    }

    public static boolean isValidPublicKey(String key) {
        return validPublicKey(key).getLeft();
    }

    private static Pair<Boolean, byte[]> validPublicKey(String key) {
        if (key.length() < 8) {
            return new ImmutablePair<>(false, new byte[]{});
        }

        if (!key.startsWith(Constants.EVT)) {
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

    public static String getNullAddress() {
        return nullAddress;
    }

    public LazyECPoint getPoint() {
        return pub;
    }

    public String toString() {
        return String.format("%s%s", Constants.EVT, Utils.base58Check(pub.getEncoded(true)));
    }

    public String getEncoded(boolean compressed) {
        return Utils.HEX.encode(pub.getEncoded(compressed));
    }
}
