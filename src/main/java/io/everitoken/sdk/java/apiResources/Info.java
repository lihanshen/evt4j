package io.everitoken.sdk.java.apiResources;

import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.request.GetRequest;
import io.everitoken.sdk.java.ApiResponse;
import io.everitoken.sdk.java.params.RequestParams;
import org.json.JSONObject;

public class Info extends ApiResource {
    private static final String name = "info";
    private static final String uri = "/v1/chain/get_info";
    private static final String method = "GET";

    public Info() {
        super(name, uri, method);
    }

    @Override
    protected GetRequest buildRequest(RequestParams requestParams) {
        return Unirest.get(getUrl(requestParams.getNetParams()));
    }

    public JSONObject get(RequestParams requestParams) {
        ApiResponse<JsonNode> res = super.makeRequest(requestParams);
        return res.getPayload().getObject();
    }
}
