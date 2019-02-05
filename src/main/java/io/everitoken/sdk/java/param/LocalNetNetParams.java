package io.everitoken.sdk.java.param;

public class LocalNetNetParams extends NetParams {
    public LocalNetNetParams(String host, int port, int networkTimeout) {
        super("http", host, port, networkTimeout);
    }
}
