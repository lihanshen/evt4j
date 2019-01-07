package io.everitoken.sdk.java.apiResources;

import com.mashape.unirest.http.JsonNode;
import io.everitoken.sdk.java.ApiResponse;
import io.everitoken.sdk.java.model.TokenDetailData;
import io.everitoken.sdk.java.params.ApiParams;
import io.everitoken.sdk.java.params.NetParams;
import org.json.JSONObject;

public class TokenDetail extends ApiResource {
    private static final String name = "tokenDetail";
    private static final String uri = "/v1/evt/get_token";
    private static final String method = "POST";

    public TokenDetail() {
        super(name, uri, method);
    }

    public TokenDetailData get(NetParams netParams, ApiParams apiParams) {
        ApiResponse<JsonNode> res = super.makeRequest(netParams, apiParams);
        JSONObject payload = res.getPayload().getObject();

        return new TokenDetailData(payload);
    }
}
