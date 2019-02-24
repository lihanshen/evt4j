package io.everitoken.sdk.java;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;

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
}