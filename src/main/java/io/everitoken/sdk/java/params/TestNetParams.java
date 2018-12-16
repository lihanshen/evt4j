package io.everitoken.sdk.java.params;

public class TestNetParams extends Params {
    public TestNetParams() {
        super("http", NET.TESTNET.getUrl(), 8888, 15000);
    }
}
