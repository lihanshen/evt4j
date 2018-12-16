package io.everitoken.sdk.java.params;

public class LocalParams extends Params {
    public LocalParams(String host, int port, int networkTimeout) {
        super("http", host, port, networkTimeout);
    }
}
