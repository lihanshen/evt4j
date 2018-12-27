package io.everitoken.sdk.java.params;

public class LocalNetNetParams extends NetParams {
    public LocalNetNetParams(String host, int port, int networkTimeout) {
        super("http", host, port, networkTimeout);
    }
}
