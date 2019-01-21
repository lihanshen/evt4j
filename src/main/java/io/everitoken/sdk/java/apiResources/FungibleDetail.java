package io.everitoken.sdk.java.apiResources;

import com.mashape.unirest.http.JsonNode;
import io.everitoken.sdk.java.exceptions.ApiResponseException;
import io.everitoken.sdk.java.params.RequestParams;
import org.json.JSONObject;

public class FungibleDetail extends ApiResource {
    private static final String uri = "/v1/evt/get_fungible";

    public FungibleDetail() {
        super(uri);
    }

    public JSONObject request(RequestParams requestParams) throws ApiResponseException {
        JsonNode res = super.makeRequest(requestParams);
        return res.getObject();
    }
}
