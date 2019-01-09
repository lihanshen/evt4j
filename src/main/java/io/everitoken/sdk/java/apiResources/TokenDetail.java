package io.everitoken.sdk.java.apiResources;

import com.mashape.unirest.http.JsonNode;
import io.everitoken.sdk.java.ApiResponse;
import io.everitoken.sdk.java.model.TokenDetailData;
import io.everitoken.sdk.java.params.RequestParams;
import org.json.JSONObject;

public class TokenDetail extends ApiResource {
    private static final String name = "tokenDetail";
    private static final String uri = "/v1/evt/get_token";
    private static final String method = "POST";

    public TokenDetail() {
        super(name, uri, method);
    }

    public TokenDetailData get(RequestParams requestParams) {
        ApiResponse<JsonNode> res = super.makeRequest(requestParams);
        JSONObject payload = res.getPayload().getObject();

        return new TokenDetailData(payload);
    }
}
