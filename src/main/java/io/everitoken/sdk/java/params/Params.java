package io.everitoken.sdk.java.params;

public abstract class Params {
    protected static enum NET {
        MAINNET1("mainnet1.everitoken.io"),
        MAINNET2("mainnet2.everitoken.io"),
        TESTNET("testnet1.everitoken.io");

        private String url;

        NET(String url) {
            url = url;
        }

        public String getUrl() {
            return url;
        }
    }

    public static final String EVT = "EVT";
    public static final String NullAddress = "EVT00000000000000000000000000000000000000000000000000";

    protected String protocol;
    protected String host;
    protected int port;
    protected int networkTimeout; // millisecond

    protected Params(String protocol, String host, int port, int networkTimeout) {
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
}
