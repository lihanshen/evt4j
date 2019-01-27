package io.everitoken.sdk.java.abi;

import io.everitoken.sdk.java.apiResources.AbiBin;
import io.everitoken.sdk.java.exceptions.AbiSerialisationFailureException;
import io.everitoken.sdk.java.exceptions.ApiResponseException;
import io.everitoken.sdk.java.params.NetParams;
import io.everitoken.sdk.java.params.RequestParams;
import org.json.JSONException;
import org.json.JSONObject;

public class RemoteAbiSerialisationProvider implements AbiSerialisationProvider {
    private final NetParams netParams;

    public RemoteAbiSerialisationProvider(NetParams netParams) {
        this.netParams = netParams;
    }

    @Override
    public String serialize(String data) {
        try {
            AbiBin abiBin = new AbiBin();
            JSONObject res = abiBin.request(RequestParams.of(netParams, () -> data));
            return res.toString();
        } catch (JSONException ex) {
            throw new IllegalArgumentException(String.format("Invalid json \"%s\" passed in.", data), ex);
        } catch (ApiResponseException ex) {
            throw new AbiSerialisationFailureException(ex.getRaw().toString(), ex);
        }
    }
}
