package io.everitoken.sdk.java;

import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;
import org.bitcoinj.core.ECKey;
import org.bitcoinj.crypto.LazyECPoint;
import org.spongycastle.crypto.digests.SHA256Digest;
import org.spongycastle.crypto.params.ECPublicKeyParameters;
import org.spongycastle.crypto.signers.ECDSASigner;
import org.spongycastle.crypto.signers.HMacDSAKCalculator;
import org.spongycastle.math.ec.ECPoint;

public class PublicKey {
    private static final String nullAddress = Constants.NullAddress;
    private LazyECPoint pub;

    public PublicKey(String key) throws EvtSdkException {
        Pair<Boolean, byte[]> pair = validPublicKey(key);

        if (!pair.getLeft()) {
            throw new EvtSdkException(null, ErrorCode.PUBLIC_KEY_INVALID);
        }

        this.pub = new LazyECPoint(ECKey.CURVE.getCurve(), pair.getRight());
    }

    private ECPoint getPoint() {
        return this.pub.getDetachedPoint();
    }

    public static boolean isValidPublicKey(String key) {
        return validPublicKey(key).getLeft();
    }

    private static Pair<Boolean, byte[]> validPublicKey(String key) {
        // key is invalid if not prefixed with "EVT"
        if (!key.startsWith(Constants.EVT)) {
            return new ImmutablePair<Boolean, byte[]>(false, new byte[]{});
        }

        // key is invalid when checksum doesn't match
        String keyWithoutPrefix = key.substring(3);
        byte[] publicKeyInBytes;
        try {
            publicKeyInBytes = Utils.base58CheckDecode(keyWithoutPrefix);
        } catch (Exception ex) {
            return new ImmutablePair<Boolean, byte[]>(false, new byte[]{});
        }

        LazyECPoint pub = new LazyECPoint(ECKey.CURVE.getCurve(), publicKeyInBytes);

        return new ImmutablePair<Boolean, byte[]>(pub.isValid(), publicKeyInBytes);
    }

    public boolean verify(byte[] data, Signature signature) {
        ECDSASigner signer = new ECDSASigner(new HMacDSAKCalculator(new SHA256Digest()));
        ECPublicKeyParameters publicKeyParams = new ECPublicKeyParameters(this.getPoint(), ECKey.CURVE);

        signer.init(false, publicKeyParams);

        return signer.verifySignature(data, signature.getR(), signature.getS());
    }

    public static String getNullAddress() {
        return nullAddress;
    }
}
