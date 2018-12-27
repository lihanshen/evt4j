package io.everitoken.sdk.java.params;

public class MainNetNetParams extends NetParams {
    public MainNetNetParams(NetParams.NET mainNet) {
        super("https", mainNet.getUrl(), 80, 15000);
    }
}
