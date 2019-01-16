package io.everitoken.sdk.java.params;

import javax.annotation.Nullable;

public class RequestParams {
    private NetParams netParams;
    private ApiParams apiParams;

    public RequestParams(NetParams netParams, @Nullable ApiParams apiParams) {
        this.netParams = netParams;
        this.apiParams = apiParams;
    }

    public RequestParams(NetParams netParams) {
        this(netParams, null);
    }

    public static RequestParams of(NetParams netParams) {
        return new RequestParams(netParams);
    }

    public static RequestParams of(NetParams netParams, ApiParams apiParams) {
        return new RequestParams(netParams, apiParams);
    }

    public NetParams getNetParams() {
        return netParams;
    }

    public void setNetParams(NetParams netParams) {
        this.netParams = netParams;
    }

    public ApiParams getApiParams() {
        return apiParams;
    }

    public void setApiParams(ApiParams apiParams) {
        this.apiParams = apiParams;
    }
}
