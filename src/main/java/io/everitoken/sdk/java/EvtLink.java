package io.everitoken.sdk.java;

import org.apache.commons.lang3.ArrayUtils;

import java.math.BigInteger;

public class EvtLink {
    private static final String ALPHABET = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ$+-/:*";
    private static final int BASE = 42;

    public static String encode(byte[] input) {

        StringBuilder prefix = new StringBuilder();

        for (byte i : input) {
            if ((int) i != 0) {
                break;
            }
            prefix.append("0");
        }

        BigInteger bigInteger = new BigInteger(input);
        BigInteger baseBn = BigInteger.valueOf(BASE);
        StringBuilder sb = new StringBuilder();

        // loop
        while (bigInteger.compareTo(BigInteger.ZERO) > 0) {
            BigInteger mod = bigInteger.mod(baseBn);
            System.out.println(mod.intValue());
            bigInteger = bigInteger.subtract(mod).divide(baseBn);
            sb.append(ALPHABET.charAt(mod.intValue()));
        }

        return prefix.append(sb.reverse()).toString();
    }

    public static byte[] decode(String base42EncodedString) {
        if (base42EncodedString.length() == 0) {
            return new byte[]{};
        }

        BigInteger resultBn = BigInteger.ZERO;
        BigInteger baseBn = BigInteger.valueOf(BASE);

        int leadingZerosCount = 0;

        for (int i = 0; i < base42EncodedString.length(); i++) {
            if (base42EncodedString.charAt(i) != '0') {
                break;
            }

            leadingZerosCount++;
        }

        for (int i = 0; i < base42EncodedString.length(); i++) {
            char c = base42EncodedString.charAt(i);
            int index = ALPHABET.indexOf(c);

            if (ALPHABET.indexOf(c) == -1) {
                throw new IllegalArgumentException(String.format("Illegal character found \"%s\" at index %d", c, i));
            }

            resultBn = resultBn.multiply(baseBn).add(BigInteger.valueOf(index));
        }

        return ArrayUtils.addAll(new byte[leadingZerosCount], resultBn.toByteArray());
    }

}
