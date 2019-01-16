package io.everitoken.sdk.java.apiResources;

import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.request.GetRequest;
import io.everitoken.sdk.java.exceptions.ApiResponseException;
import io.everitoken.sdk.java.params.RequestParams;
import org.json.JSONObject;

public class HeadBlockHeaderState extends ApiResource {
    private static final String uri = "/v1/chain/get_head_block_header_state";
    private static final String method = "GET";

    public HeadBlockHeaderState() {
        super(uri, method);
    }

    @Override
    protected GetRequest buildRequest(RequestParams requestParams) {
        return Unirest.get(getUrl(requestParams.getNetParams()));
    }

    public JSONObject request(RequestParams requestParams) throws ApiResponseException {
        JsonNode res = super.makeRequest(requestParams);
        return res.getObject();
    }
}
