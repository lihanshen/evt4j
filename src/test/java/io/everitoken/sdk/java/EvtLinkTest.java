package io.everitoken.sdk.java;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.util.List;

class EvtLinkTest {
    static void createSegment40To90() {
        byte[] content = ByteBuffer.allocate(4).putInt(100000).array();
        Assertions.assertEquals("2a000186a0", Utils.HEX.encode(EvtLink.createSegment(42, content)));
    }

    @Test
    void createSegment20() {
        byte[] content = ByteBuffer.allocate(1).put((byte) 256).array();
        Assertions.assertEquals("1400", Utils.HEX.encode(EvtLink.createSegment(20, content)));

        byte[] c = new byte[1];
        c[0] = (byte) 255;
        Assertions.assertEquals("14ff", Utils.HEX.encode(EvtLink.createSegment(20, c)));
    }

    @Test
    void createSegment90() {
        Assertions.assertEquals("5b0a68656c6c6f776f726c64", Utils.HEX.encode(EvtLink.createSegment(
                91,
                "helloworld".getBytes()
        )));
    }

    @Test
    void parseSegment20() {
        byte[] bs = Utils.HEX.decode("142a");
        EvtLink.Segment segment = EvtLink.parseSegment(bs, 0);
        Assertions.assertEquals(42, segment.getContent()[0]);
        Assertions.assertEquals(2, segment.getLength());
    }

    @Test
    void parseSegment40To90() {
        byte[] bs = Utils.HEX.decode("2a000186a0");
        EvtLink.Segment segment = EvtLink.parseSegment(bs, 0);
        Assertions.assertEquals(100000, ByteBuffer.wrap(segment.getContent()).getInt());
    }

    @Test
    void parseString() {
        byte[] bs = Utils.HEX.decode("5b0a68656c6c6f776f726c64");
        EvtLink.Segment segment = EvtLink.parseSegment(bs, 0);
        System.out.println(Utils.HEX.encode(EvtLink.createSegment(
                91,
                "helloworld".getBytes()
        )));
        Assertions.assertEquals(
                "helloworldhelloworldhelloworldhelloworldhelloworldhelloworldhelloworldhelloworldhelloworldhelloworldhelloworldhelloworldhelloworldhelloworldhelloworldhelloworld", new String(segment.getContent(), StandardCharsets.UTF_8));
    }

    @Test
    void parseUuid() {
        byte[] bs = EvtLink.createSegment(156, Utils.HEX.decode("753b0605fbe19dd39460590ce867999c"));
        EvtLink.Segment segment = EvtLink.parseSegment(bs, 0);
        Assertions.assertEquals(156, segment.getTypeKey());

        Assertions.assertEquals("753b0605fbe19dd39460590ce867999c", Utils.HEX.encode(segment.getContent()));
        Assertions.assertEquals(17, segment.getLength());
    }

    @Test
    void decode() {
        String input = "FXXJORONB8B58VU9Z2MZKZ5*:NP3::K7UYKD:Y9I1V508HBQZK2AE*ZS85PJZ2N47/41LQ-MZ/4Q6THOX**YN0VMQ*3" +
                "/CG9-KX2:E7C-OCM*KJJT:Z7640Q6B*FWIQBYMDPIXB4CM:-8*TW-QNY$$AY5$UA3+N-7L/ZSDCWO1I7M*3Q6*SMAYOWWTF5RJAJ" +
                ":NG**8U5J6WC2VM5Z:OLZPVJXX*12I*6V9FL1HX095$5:$*C3KGCM3FIS-WWRE14E:7VYNFA-3QCH5ULZJ*CRH91BTXIK-N+J1";

        Assertions.assertEquals(
                "1f55bba92913fd086a2bc4e498a07c57534f70ce836d9a2592824a9cfac1503bcffd0cd068577fddf85ef64ce5420742a1b8a97ee10b05925f2091a7d181847fa74b685a6dc95d82532d77a8b1940ed4078a9ecb796d0c22f4ce6a0641c342b4c094b6d4618e52c69103f905cebf397026861b5949632d26d923268fd9321da4937f60d48e63c2d3b966d36f603739e6b2c8ae7f70e62466a027f61daae470476038d43859ef811f5c6e6a57d00f1602b9001546208f98527edd65c96e47656547114b", Utils.HEX.encode(EvtLink.decode(input)));
    }

    @Test
    void parseLink() {
        String link = "0DFYZXZO9-:Y:JLF*3/4JCPG7V1346OZ:R/G2M93-2L*BBT9S0YQ0+JNRIW95*HF*94J0OVUN$KS01-GZ" +
                "-N7FWK9_FXXJORONB7B58VU9Z2MZKZ5*:NP3::K7UYKD:Y9I1V508HBQZK2AE*ZS85PJZ2N47/41LQ-MZ" +
                "/4Q6THOX**YN0VMQ*3/CG9-KX2:E7C-OCM*KJJT:Z7640Q6B*FWIQBYMDPIXB4CM:-8*TW-QNY$$AY5$UA3" +
                "+N-7L/ZSDCWO1I7M*3Q6*SMAYOWWTF5RJAJ:NG**8U5J6WC2VM5Z:OLZPVJXX*12I*6V9FL1HX095$5:$" +
                "*C3KGCM3FIS-WWRE14E:7VYNFA-3QCH5ULZJ*CRH91BTXIK-N+J1";

        EvtLink.SignatureWithRecoveredPublicKey parsed = EvtLink.parseSignatures(link, true);
        List<Signature> signatures = parsed.getSignatures();
        List<PublicKey> publicKeys = parsed.getPublicKeys();

        Assertions.assertEquals(
                "SIG_K1_K6UKhSMgMdZkm1M6JUNaK6XBGgvpVWuexhUzrg9ARgJCsWiN2A5PeH9K9YUpuE8ZArYXvSWMwBSEVh8dFhHPriQh6raEVc", signatures.get(0).toString());
        Assertions.assertEquals(
                "SIG_K1_KfdYEC6GnvgkrDPLPN4tFsTACc4nnpEopBdwBsg9fwzG8zu489hCma5gYeW3zsvabbCfMQL4vu9QVbyTHHDLjp43NCNFtD", signatures.get(1).toString());
        Assertions.assertEquals(
                "SIG_K1_K3CZKdq28aNkGwU9bL57aW45kvWj3CagGgarShLYFg8MVoTTHRbXZwPvyfBf9WN93VGXBPDLdFMmtbKA814XVvQ3QZRVJn", signatures.get(2).toString());

        Assertions.assertEquals(
                "EVT6Qz3wuRjyN6gaU3P3XRxpnEZnM4oPxortemaWDwFRvsv2FxgND", publicKeys.get(0).toString());
        Assertions.assertEquals(
                "EVT6MYSkiBHNDLxE6JfTmSA1FxwZCgBnBYvCo7snSQEQ2ySBtpC6s", publicKeys.get(1).toString());
        Assertions.assertEquals(
                "EVT7bUYEdpHiKcKT9Yi794MiwKzx5tGY3cHSh4DoCrL4B2LRjRgnt", publicKeys.get(2).toString());

    }
}