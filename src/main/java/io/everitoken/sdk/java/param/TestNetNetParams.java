package io.everitoken.sdk.java.param;

public class TestNetNetParams extends NetParams {
    public TestNetNetParams() {
        super("https", NET.TESTNET.getUrl(), 8888, 15000);
    }
}