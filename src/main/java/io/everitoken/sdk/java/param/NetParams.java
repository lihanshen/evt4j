package io.everitoken.sdk.java.param;

import org.jetbrains.annotations.Contract;

public abstract class NetParams {
    private final String protocol;
    private final String host;
    private final int port;
    private final int networkTimeout; // millisecond

    protected NetParams(String protocol, String host, int port, int networkTimeout) {
        this.protocol = protocol;
        this.host = host;
        this.port = port;
        this.networkTimeout = networkTimeout;
    }

    public String getEndpointUrl() {
        return String.format("%s://%s:%s", getProtocol(), getHost(), getPort());
    }

    public String getProtocol() {
        return protocol;
    }

    public String getHost() {
        return host;
    }

    public int getPort() {
        return port;
    }

    public int getNetworkTimeout() {
        return networkTimeout;
    }

    public static enum NET {
        MAINNET1("mainnet1.everitoken.io"),
        MAINNET2("mainnet2.everitoken.io"),
        TESTNET("testnet1.everitoken.io");

        private final String url;

        NET(String url) {
            this.url = url;
        }

        @Contract(pure = true)
        public String getUrl() {
            return url;
        }
    }
}
