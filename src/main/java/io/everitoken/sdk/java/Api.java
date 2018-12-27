package io.everitoken.sdk.java;

import io.everitoken.sdk.java.apiResources.Info;
import io.everitoken.sdk.java.keyProvider.KeyProvider;
import io.everitoken.sdk.java.params.NetParams;
import io.everitoken.sdk.java.params.TestNetNetParams;

import javax.annotation.Nullable;

// TODO write test
public class Api {
    private NetParams netParams;
    private KeyProvider keyProvider;

    public Api(NetParams netParams, @Nullable KeyProvider keyProvider) {
        this.netParams = netParams;
        this.keyProvider = keyProvider;
    }

    public Api() {
        this(new TestNetNetParams(), null);
    }

    public ApiResponse getInfo() throws EvtSdkException {
        Info info = new Info();
        return info.get(netParams);
    }

    public static void main(String[] args) {
        try {
            Api api = new Api();
            ApiResponse res = api.getInfo();
            System.out.println(res.getPayload().getStatus());
        } catch (Exception ex) {
            System.out.println("error");
        }
    }
}
