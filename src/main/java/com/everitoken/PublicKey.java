package com.everitoken;

import org.apache.commons.lang3.ArrayUtils;
import org.bitcoinj.core.Base58;
import org.bitcoinj.core.Utils;
import org.bitcoinj.crypto.LazyECPoint;
import org.bitcoinj.crypto.LinuxSecureRandom;
import org.spongycastle.asn1.x9.X9ECParameters;
import org.spongycastle.crypto.ec.CustomNamedCurves;
import org.spongycastle.crypto.params.ECDomainParameters;
import org.spongycastle.math.ec.FixedPointUtil;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.Objects;

public class PublicKey {
    private LazyECPoint pub;
    private static final X9ECParameters CURVE_PARAMS = CustomNamedCurves.getByName("secp256k1");
    public static final ECDomainParameters CURVE;
    private static final SecureRandom secureRandom;
    public static final BigInteger HALF_CURVE_ORDER;

    public static void main(String[] args) {
        // TODO cleanup
        String key = "EVT76uLwUD5t6fkob9Rbc9UxHgdTVshNceyv2hmppw4d82j2zYRpa";
        PublicKey k = new PublicKey(key);
        System.out.println(k);
    }

    public PublicKey(String key) {
        // strip "EVT" as prefix
        String noPrefixKey = key.substring(3);
        // base58 decode
        byte[] decodedKeyBytes = Base58.decode(noPrefixKey);
        byte[] pubKeyBytes = ArrayUtils.subarray(decodedKeyBytes, 0, decodedKeyBytes.length - 4);
        // TODO check key
        this.pub = new LazyECPoint(CURVE.getCurve(), pubKeyBytes);
    }

    public static boolean isValidPublicKey(String key) {
        return true;
    }

    static {
        if (Utils.isAndroidRuntime()) {
            new LinuxSecureRandom();
        }

        FixedPointUtil.precompute(CURVE_PARAMS.getG(), 12);
        CURVE = new ECDomainParameters(CURVE_PARAMS.getCurve(), CURVE_PARAMS.getG(), CURVE_PARAMS.getN(), CURVE_PARAMS.getH());
        HALF_CURVE_ORDER = CURVE_PARAMS.getN().shiftRight(1);
        secureRandom = new SecureRandom();
    }
}
