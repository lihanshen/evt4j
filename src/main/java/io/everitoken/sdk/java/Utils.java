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
import org.spongycastle.crypto.digests.RIPEMD160Digest;

import java.security.SecureRandom;


public class Utils {
    public static final BaseEncoding HEX = BaseEncoding.base16().lowerCase();

    private static byte[] ripemd160(byte[] data) {
        RIPEMD160Digest digest = new RIPEMD160Digest();
        digest.update(data, 0, data.length);
        byte[] out = new byte[20];
        digest.doFinal(out, 0);
        return out;
    }

    @NotNull
    public static String base58Check(byte[] key) {
        byte[] hash = ripemd160(key);
        byte[] concat = ArrayUtils.addAll(key, ArrayUtils.subarray(hash, 0, 4));
        return Base58.encode(concat);
    }

    public static byte[] base58CheckDecode(String key) throws Base58CheckException {
        byte[] decoded;

        try {
            // base58 decode
            decoded = Base58.decode(key);
        } catch (AddressFormatException ex) {
            throw new Base58CheckException(ex.getMessage(), ex);
        }
        // split the byte slice
        byte[] data = ArrayUtils.subarray(decoded, 0, decoded.length - 4);
        byte[] checksum = ArrayUtils.subarray(decoded, decoded.length - 4, decoded.length);

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
            throw new Base58CheckException();
        }

        return data;
    }

    @NotNull
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
        return HEX.encode(randomBytes);
    }

    public static byte[] random32Bytes() {
        SecureRandom random = new SecureRandom();
        byte[] values = new byte[32];
        random.nextBytes(values);
        return values;
    }

    public static byte[] hashTwice(byte[] data) {
        return Sha256Hash.hashTwice(data);
    }

//    public static JSONObject abiToBin(NetParams netParams, AbiImpl abi, boolean throughApi) throws
//    ApiResponseException {
//        if (!throughApi) {
//            throw new IllegalStateException("Currently Abi to bin action can only be done through Api");
//        }
//
//        AbiBin abiBin = new AbiBin();
//        return abiBin.request(RequestParams.of(netParams, abi::serialize));
//    }

    public static String jsonPrettyPrint(Object raw) {
        return JSON.toJSONString(raw, SerializerFeature.PrettyFormat);
    }
}
