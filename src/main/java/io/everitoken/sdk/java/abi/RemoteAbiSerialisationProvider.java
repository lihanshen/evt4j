package io.everitoken.sdk.java.abi;

import io.everitoken.sdk.java.apiResources.AbiBin;
import io.everitoken.sdk.java.exceptions.ApiResponseException;
import io.everitoken.sdk.java.params.NetParams;
import io.everitoken.sdk.java.params.RequestParams;
import org.json.JSONObject;

public class RemoteAbiSerialisationProvider implements AbiSerialisationProvider {
    private final NetParams netParams;

    public RemoteAbiSerialisationProvider(NetParams netParams) {
        this.netParams = netParams;
    }

    @Override
    public String serialize(String data) {
        System.out.println(data);
        try {
            AbiBin abiBin = new AbiBin();
            JSONObject res = abiBin.request(RequestParams.of(netParams, () -> data));
            System.out.println(res.toString());
            return res.toString();
        } catch (ApiResponseException ex) {
            System.out.println(ex.getRaw());
            return "";
        }
    }
}
