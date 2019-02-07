package io.everitoken.sdk.java;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.google.common.io.BaseEncoding;
import io.everitoken.sdk.java.exceptions.Base58CheckException;
import org.apache.commons.lang3.ArrayUtils;
import org.bitcoinj.core.AddressFormatException;
import org.bitcoinj.core.Base58;
import org.bitcoinj.core.Sha256Hash;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.spongycastle.crypto.digests.RIPEMD160Digest;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.security.SecureRandom;


public class Utils {
    public static final BaseEncoding HEX = BaseEncoding.base16().lowerCase();

    private static byte[] ripemd160(final byte[] data) {
        final RIPEMD160Digest digest = new RIPEMD160Digest();
        digest.update(data, 0, data.length);
        final byte[] out = new byte[20];
        digest.doFinal(out, 0);
        return out;
    }

    public static String base58Check(final byte[] key) {
        return base58Check(key, null);
    }

    @NotNull
    public static String base58Check(final byte[] key, @Nullable final String keyType) {
        byte[] check = key;

        if (keyType != null) {
            check = ArrayUtils.addAll(key, keyType.getBytes());
        }

        final byte[] hash = ripemd160(check);
        final byte[] concat = ArrayUtils.addAll(key, ArrayUtils.subarray(hash, 0, 4));
        return Base58.encode(concat);
    }

    public static byte[] base58CheckDecode(final String key) throws Base58CheckException {
        return base58CheckDecode(key, null);
    }

    public static byte[] base58CheckDecode(final String key, @Nullable final String keyType) throws Base58CheckException {
        final byte[] decoded;

        try {
            // base58 decode
            decoded = Base58.decode(key);
        } catch (final AddressFormatException ex) {
            throw new Base58CheckException(ex.getMessage(), ex);
        }
        // split the byte slice
        byte[] data = ArrayUtils.subarray(decoded, 0, decoded.length - 4);
        final byte[] checksum = ArrayUtils.subarray(decoded, decoded.length - 4, decoded.length);

        if (keyType != null) {
            data = ArrayUtils.addAll(data, keyType.getBytes());
        }

        // ripemd160 input, get 4 bytes to compare
        final byte[] hash = ripemd160(data);

        // if pass, return data, otherwise throw ex
        // compare two checksum
        boolean isEqual = true;

        for (int i = 0; i < checksum.length; i++) {
            if (hash[i] != checksum[i]) {
                isEqual = false;
            }
        }

        if (!isEqual) {
            throw new Base58CheckException();
        }

        if (keyType != null) {
            return ArrayUtils.subarray(data, 0, data.length - keyType.getBytes().length);
        }

        return data;
    }

    @NotNull
    public static String randomName128() {
        final String candidates = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz.-";
        final int length = candidates.length();
        final StringBuilder sb = new StringBuilder();
        final byte[] random = random32Bytes();

        for (int i = 0; i < 21; i++) {
            sb.append(candidates.charAt(random[i] & 0xff % length));
        }

        return sb.toString();
    }

    public static String random32BytesAsHex() {
        final byte[] randomBytes = random32Bytes();
        return HEX.encode(randomBytes);
    }

    public static byte[] random32Bytes() {
        final SecureRandom random = new SecureRandom();
        final byte[] values = new byte[32];
        random.nextBytes(values);
        return values;
    }

    public static byte[] hashTwice(final byte[] data) {
        return Sha256Hash.hashTwice(data);
    }

    public static String jsonPrettyPrint(final Object raw) {
        return JSON.toJSONString(raw, SerializerFeature.PrettyFormat);
    }

    // TODO: this needs more checking dirty hack
    public static int getNumHash(final String hash) {
        final byte[] input = Utils.HEX.decode(hash);
        final short v = ByteBuffer.wrap(input, 2, input.length - 2).getShort();

        if (v > 0) {
            return (int) v;
        }

        return 65536 + v;
    }

    public static long getLastIrreversibleBlockPrefix(final String hash) {
        final byte[] input = Utils.HEX.decode(hash);
        return Integer.toUnsignedLong(
                ByteBuffer.wrap(input, 8, input.length - 8).order(ByteOrder.LITTLE_ENDIAN).getInt()
        );
    }

}
