package io.everitoken.sdk.java;

import io.everitoken.sdk.java.exceptions.ApiResponseException;
import io.everitoken.sdk.java.exceptions.EvtLinkSyncTimeException;
import io.everitoken.sdk.java.param.NetParams;
import io.everitoken.sdk.java.param.TestNetNetParams;
import io.everitoken.sdk.java.provider.KeyProvider;
import io.everitoken.sdk.java.provider.SignProvider;
import io.everitoken.sdk.java.provider.SignProviderInterface;
import org.apache.commons.lang3.ArrayUtils;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.joda.time.DateTime;

import java.math.BigInteger;
import java.nio.ByteBuffer;
import java.security.SecureRandom;
import java.util.*;

public class EvtLink {
    private static final String ALPHABET = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ$+-/:*";
    private static final int BASE = ALPHABET.length();
    private static final String QR_PREFIX = "https://evt.li/";
    private final NetParams netParams;
    private DateTime correctedTime;

    public EvtLink(NetParams netParams) {
        this.netParams = netParams;
    }

    private static List<Segment> parseSegments(byte[] segmentBytes) {
        List<Segment> segments = new ArrayList<>();
        int offset = 0;

        while (offset < segmentBytes.length) {
            Segment segment = parseSegment(segmentBytes, offset);
            offset += segment.getLength();
            segments.add(segment);
        }

        return segments;
    }

    private static String generateQRCode(int flag, List<byte[]> segments,
                                         @Nullable SignProviderInterface signProvider) {
        segments.sort(Comparator.comparingInt(a -> a[0]));

        // put flag in first
        byte[] contentBytes = ByteBuffer.allocate(2).putShort((short) flag).array();

        for (byte[] segment : segments) {
            contentBytes = ArrayUtils.addAll(contentBytes, segment);
        }

        String content = encode(contentBytes);

        if ((flag & 16) == 16) {
            content = QR_PREFIX + content;
        }

        if (signProvider != null) {
            byte[] signaturesBytes = new byte[]{};
            List<Signature> signatures = signProvider.sign(Utils.hash(contentBytes));

            for (Signature signature : signatures) {
                signaturesBytes = ArrayUtils.addAll(signaturesBytes, signature.getBytes());
            }

            content = String.format("%s_%s", content, encode(signaturesBytes));
        }

        return content;
    }

    public static ParsedLink parseLink(String rawContent, boolean recoverPublicKey) {
        String[] parts = rawContent.split("_");

        // check prefix
        if (parts[0].startsWith(QR_PREFIX)) {
            parts[0] = parts[0].substring(QR_PREFIX.length());
        }

        byte[] contentBytes = EvtLink.decode(parts[0]);
        byte[] sigBytes = EvtLink.decode(parts[1]);

        int flag = ByteBuffer.allocate(2).put(contentBytes, 0, 2).getShort(0) & 0xffff;

        List<Signature> signatures = new ArrayList<>();
        List<PublicKey> publicKeys = new ArrayList<>();

        for (int i = 0; i < sigBytes.length; i = i + Signature.BUFFER_LENGTH) {
            byte[] singleSigBytes = ByteBuffer.allocate(Signature.BUFFER_LENGTH)
                    .put(sigBytes, i, Signature.BUFFER_LENGTH).array();
            Signature sig = Signature.of(singleSigBytes);
            signatures.add(sig);

            if (recoverPublicKey) {
                PublicKey publicKey = Signature.recoverPublicKey(Utils.hash(contentBytes), sig);
                publicKeys.add(publicKey);
            }
        }

        return new ParsedLink(flag, parseSegments(ArrayUtils.subarray(contentBytes, 2, contentBytes.length)),
                              signatures,
                              publicKeys
        );
    }

    public static String getUniqueLinkId() {
        SecureRandom random = new SecureRandom();
        byte[] values = new byte[16];
        random.nextBytes(values);

        return Utils.HEX.encode(values);
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
            return new Segment(type, ByteBuffer.allocate(contentLength).put(content, 2 + offset, contentLength).array(),
                               contentLength + 2
            );
        } else if (type <= 165) {
            int contentLength = 16;
            return new Segment(type, ByteBuffer.allocate(contentLength).put(content, 1 + offset, contentLength).array(),
                               contentLength + 1
            );
        } else if (type <= 180) {
            int contentLength = content[offset + 1] & 0xff;
            return new Segment(type, ByteBuffer.allocate(contentLength).put(content, 2 + offset, contentLength).array(),
                               contentLength + 2
            );
        } else {
            throw new IllegalArgumentException(String.format("Segment type %d is not supported", type));
        }
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

    public static String getEveriPayText(@NotNull EveriPayParam param) {

        return "getEveriPayText";
    }

    public static String getEvtLinkForPayeeCode(@NotNull EveriLinkPayeeCodeParam param) {
        // TODO
        return "getEvtLinkForPayeeCode";
    }

    public static void main(String[] args) {
        EveriPassParam everiPassParam = new EveriPassParam(true, "testDomain", "testToken");

        TestNetNetParams testNetNetParams = new TestNetNetParams();
        EvtLink evtLink = new EvtLink(testNetNetParams);
        String passText = evtLink.getEveriPassText(everiPassParam, SignProvider.of(KeyProvider.of(
                "5J1by7KRQujRdXrurEsvEr2zQGcdPaMJRjewER6XsAR2eCcpt3D")));
        ParsedLink parsedLink = parseLink(passText, true);
        System.out.println(parsedLink.getPublicKeys());
    }

    public String getEveriPassText(@NotNull EveriPassParam param, @Nullable SignProviderInterface signProvider) {

        int flag = 1 + 2 + (param.isAutoDestroy() ? 8 : 0);
        byte[] timeBytes = createSegment(
                42,
                ByteBuffer.allocate(4).putInt((int) getCorrectedTime().getMillis() / 1000).array()
        );

        byte[] domainBytes = createSegment(91, param.getDomain().getBytes());
        byte[] tokenBytes = createSegment(92, param.getToken().getBytes());

        return generateQRCode(flag, Arrays.asList(timeBytes, domainBytes, tokenBytes), signProvider);
    }

    private DateTime getCorrectedTime() {
        if (correctedTime == null) {
            try {
                Api api = new Api(netParams);
                correctedTime = Utils.getCorrectedTime(api.getInfo().getHeadBlockTime());
            } catch (ApiResponseException ex) {
                throw new EvtLinkSyncTimeException("Unable sync time with node", ex);
            }
        }

        return correctedTime;
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

    static class ParsedLink {
        private final List<Signature> signatures;
        private final List<PublicKey> publicKeys;
        private final List<Segment> segments;
        private final int flag;

        private ParsedLink(int flag, List<Segment> segments, List<Signature> signatures, List<PublicKey> publicKeys) {
            this.flag = flag;
            this.segments = segments;
            this.signatures = signatures;
            this.publicKeys = publicKeys;
        }

        public List<Signature> getSignatures() {
            return signatures;
        }

        public List<PublicKey> getPublicKeys() {
            return publicKeys;
        }

        public List<Segment> getSegments() {
            return segments;
        }

        public int getFlag() {
            return flag;
        }
    }

    static class EveriPayParam {

    }

    static class EveriPassParam {
        private final boolean autoDestroy;
        private final String domain;
        private final String token;

        public EveriPassParam(boolean autoDestroy, String domain, String token) {
            Objects.requireNonNull(domain);
            Objects.requireNonNull(token);

            this.autoDestroy = autoDestroy;
            this.domain = domain;
            this.token = token;
        }

        public boolean isAutoDestroy() {
            return autoDestroy;
        }

        public String getDomain() {
            return domain;
        }

        public String getToken() {
            return token;
        }
    }

    static class EveriLinkPayeeCodeParam {

    }

}
