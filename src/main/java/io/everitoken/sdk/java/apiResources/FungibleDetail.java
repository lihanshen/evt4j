package io.everitoken.sdk.java.apiResources;

import com.mashape.unirest.http.JsonNode;
import io.everitoken.sdk.java.EvtSdkException;
import io.everitoken.sdk.java.params.RequestParams;
import org.json.JSONObject;

public class FungibleDetail extends ApiResource {
    private static final String name = "fungibleDetail";
    private static final String uri = "/v1/evt/get_fungible";
    private static final String method = "POST";

    public FungibleDetail() {
        super(name, uri, method);
    }

    public JSONObject request(RequestParams requestParams) throws EvtSdkException {
        JsonNode res = super.makeRequest(requestParams);
        return res.getObject();
    }
}
