package io.everitoken.sdk.java.params;

public class MainNetParams extends Params {
    public MainNetParams(Params.NET mainNet) {
        super("https", mainNet.getUrl(), 80, 15000);
    }
}
