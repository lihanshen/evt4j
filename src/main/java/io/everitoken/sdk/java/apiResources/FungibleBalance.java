package io.everitoken.sdk.java.apiResources;

import com.mashape.unirest.http.JsonNode;
import io.everitoken.sdk.java.EvtSdkException;
import io.everitoken.sdk.java.params.RequestParams;
import org.json.JSONArray;

public class FungibleBalance extends ApiResource {
    private static final String uri = "/v1/evt/get_fungible_balance";
    private static final String method = "POST";

    public FungibleBalance() {
        super(uri, method);
    }

    public JSONArray request(RequestParams requestParams) throws EvtSdkException {
        JsonNode res = super.makeRequest(requestParams);
        return res.getArray();
    }
}
