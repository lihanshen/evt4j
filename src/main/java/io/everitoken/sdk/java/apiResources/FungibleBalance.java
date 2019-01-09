package io.everitoken.sdk.java.apiResources;

import com.mashape.unirest.http.JsonNode;
import io.everitoken.sdk.java.ApiResponse;
import io.everitoken.sdk.java.params.RequestParams;
import org.json.JSONArray;

public class FungibleBalance extends ApiResource {
    private static final String name = "fungibleBalance";
    private static final String uri = "/v1/evt/get_fungible_balance";
    private static final String method = "POST";

    public FungibleBalance() {
        super(name, uri, method);
    }

    public JSONArray get(RequestParams requestParams) {
        ApiResponse<JsonNode> res = super.makeRequest(requestParams);
        JSONArray payload = res.getPayload().getArray();

        return payload;
    }
}
