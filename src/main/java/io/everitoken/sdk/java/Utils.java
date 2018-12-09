package io.everitoken.sdk.java;

import org.apache.commons.lang3.ArrayUtils;
import org.bitcoinj.core.Base58;
import org.spongycastle.crypto.digests.RIPEMD160Digest;

import java.security.SecureRandom;

class Utils {
    private static byte[] ripemd160(byte[] data) {
        RIPEMD160Digest digest = new RIPEMD160Digest();
        digest.update(data,0, data.length);
        byte[] out = new byte[20];
        digest.doFinal(out, 0);
        return out;
    }

    public static String base58Check(byte[] key) {
        byte[] hash = ripemd160(key);
        byte[] concat = ArrayUtils.addAll(key, ArrayUtils.subarray(hash, 0,4));
        return Base58.encode(concat);
    }

    public static byte[] base58CheckDecode(String key) throws Exception {
        // base58 decode
        byte[] decoded = Base58.decode(key);
        // split the byte slice
        byte[] data = ArrayUtils.subarray(decoded, 0, decoded.length - 4);
        byte[] checksum = ArrayUtils.subarray(decoded, decoded.length -4, decoded.length);

        // ripemd160 input, get 4 bytes to compare
        byte[] hash = ripemd160(data);

        // if pass, return data, otherwise throw ex
        // compare two checksum
        boolean isEqual = true;
        for (int i = 0; i < checksum.length; i++) {
            if (hash[i] != checksum[i]) {
                isEqual = false;
            }
        }

        if (!isEqual) {
            throw new Exception("Checksum doesn't match.");
        }

        return data;
    }

    private static String byte2Hex(byte[] data) {
        StringBuilder sb = new StringBuilder();
        for (byte b : data) {
            sb.append(String.format("%02x", b));
        }

        return sb.toString();
    }

    public static String randomName128() {
        String candidates = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz.-";
        int length = candidates.length();
        StringBuilder sb = new StringBuilder();
        byte[] random = random32Bytes();

        for (int i = 0; i < 21; i++) {
           sb.append(candidates.charAt(random[i] & 0xff % length));
        }

        return sb.toString();
    }

    public static String random32BytesAsHex() {
        byte[] randomBytes = random32Bytes();
        return byte2Hex(randomBytes);
    }

    public static byte[] random32Bytes() {
        SecureRandom random = new SecureRandom();
        byte[] values = new byte[32];
        random.nextBytes(values);
        return values;
    }
}
