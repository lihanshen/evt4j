package io.everitoken.sdk.java;

import org.apache.commons.lang3.ArrayUtils;

import java.math.BigInteger;
import java.nio.ByteBuffer;

public class EvtLink {
    private static final String ALPHABET = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ$+-/:*";
    private static final int BASE = 42;

    public static void main(String[] args) {
        byte[] bs = Utils.HEX.decode("2a000186a0");
        parseSegment(bs, 0);
    }

    public static Segment parseSegment(byte[] content, int offset) {
        int type = content[offset] & 0xff;

        if (type <= 20) {
            return new Segment(type, ByteBuffer.allocate(1).put(content[offset + 1]).array(), 2);
        } else if (type <= 40) {
            return new Segment(type, ByteBuffer.allocate(2).put(content, 1, 2).array(), 3);
        } else if (type <= 90) {
            return new Segment(type, ByteBuffer.allocate(4).put(content, 1, 4).array(), 5);
        } else if (type <= 155) {
            int contentLength = content[offset + 1] & 0xff;
            return new Segment(type, ByteBuffer.allocate(contentLength).put(content, 2, contentLength).array(),
                               contentLength + 2
            );
        } else if (type <= 165) {
            int contentLength = 16;
            return new Segment(type, ByteBuffer.allocate(contentLength).put(content, 1, contentLength).array(),
                               contentLength + 1
            );
        } else if (type <= 180) {
            int contentLength = content[offset + 1] & 0xff;
            return new Segment(type, ByteBuffer.allocate(contentLength).put(content, 2, contentLength).array(),
                               contentLength + 2
            );
        }

        return new Segment(0, new byte[]{}, 1);
    }

    public static byte[] createSegment(int type, byte[] content) {
        if (type < 0 || type > 255) {
            throw new IllegalArgumentException("Invalid type value, it must be within 0 to 255");
        }

        if (type <= 20) {
            ByteBuffer b = ByteBuffer.wrap(new byte[2]);
            b.put((byte) type);
            b.put(content, 0, 1);
            return b.array();
        } else if (type <= 40) {
            ByteBuffer b = ByteBuffer.wrap(new byte[3]);
            b.put((byte) type);
            b.put(content, 0, 2);
            return b.array();
        } else if (type <= 90) {
            ByteBuffer b = ByteBuffer.wrap(new byte[5]);
            b.put((byte) type);
            b.put(content, 0, 4);
            return b.array();
        } else if (type <= 155) {
            ByteBuffer b = ByteBuffer.wrap(new byte[2]);
            b.put((byte) type);
            b.put((byte) content.length);
            return ArrayUtils.addAll(b.array(), content);
        } else if (type <= 165) { // TODO double check
            ByteBuffer b = ByteBuffer.wrap(new byte[1]);
            b.put((byte) type);
            return ArrayUtils.addAll(b.array(), content);
        } else if (type <= 180) {
            ByteBuffer b = ByteBuffer.wrap(new byte[2]);
            b.put((byte) type);
            b.put((byte) content.length);
            return ArrayUtils.addAll(b.array(), content);
        } else {
            throw new IllegalArgumentException(String.format("Segment type %d is not supported", type));
        }
    }

    public static String encode(byte[] input) {
        if (input.length == 0) {
            return "";
        }

        // build prefix string buffer
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

        while (bigInteger.compareTo(BigInteger.ZERO) > 0) {
            BigInteger mod = bigInteger.mod(baseBn);
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
                throw new IllegalArgumentException(String.format("Illegal character found \"%s\" at index %d", c,
                                                                 i
                ));
            }

            resultBn = resultBn.multiply(baseBn).add(BigInteger.valueOf(index));
        }

        return ArrayUtils.addAll(new byte[leadingZerosCount], resultBn.toByteArray());
    }

    static class Segment {
        private final int typeKey;
        private final byte[] content;
        private final int length;

        private Segment(int typeKey, byte[] content, int length) {
            this.typeKey = typeKey;
            this.content = content;
            this.length = length;
        }

        public int getTypeKey() {
            return typeKey;
        }

        public byte[] getContent() {
            return content;
        }

        public int getLength() {
            return length;
        }
    }
}
