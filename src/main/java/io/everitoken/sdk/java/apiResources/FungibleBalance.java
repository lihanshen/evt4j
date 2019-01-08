package io.everitoken.sdk.java.apiResources;

import com.mashape.unirest.http.JsonNode;
import io.everitoken.sdk.java.ApiResponse;
import io.everitoken.sdk.java.params.ApiParams;
import io.everitoken.sdk.java.params.NetParams;
import org.json.JSONArray;

public class FungibleBalance extends ApiResource {
    private static final String name = "fungibleBalance";
    private static final String uri = "/v1/evt/get_fungible_balance";
    private static final String method = "POST";

    public FungibleBalance() {
        super(name, uri, method);
    }

    public JSONArray get(NetParams netParams, ApiParams apiParams) {
        ApiResponse<JsonNode> res = super.makeRequest(netParams, apiParams);
        JSONArray payload = res.getPayload().getArray();

        return payload;
    }
}
