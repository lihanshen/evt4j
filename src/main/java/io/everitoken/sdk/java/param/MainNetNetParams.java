package io.everitoken.sdk.java.param;

import org.jetbrains.annotations.NotNull;

public class MainNetNetParams extends NetParams {
    public MainNetNetParams(@NotNull NetParams.NET mainNet) {
        super("https", mainNet.getUrl(), 80, 15000);
    }

    @Override
    public String getEndpointUrl() {
        return String.format("%s://%s", getProtocol(), getHost());
    }
}
