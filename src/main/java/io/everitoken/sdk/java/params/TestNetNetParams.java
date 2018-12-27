package io.everitoken.sdk.java.params;

public class TestNetNetParams extends NetParams {
    public TestNetNetParams() {
        super("http", NET.TESTNET.getUrl(), 8888, 15000);
    }
}
