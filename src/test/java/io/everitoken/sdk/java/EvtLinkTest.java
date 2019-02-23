package io.everitoken.sdk.java;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.nio.ByteBuffer;

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
}