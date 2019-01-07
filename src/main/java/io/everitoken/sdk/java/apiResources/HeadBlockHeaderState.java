package io.everitoken.sdk.java.apiResources;

import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.request.GetRequest;
import io.everitoken.sdk.java.ApiResponse;
import io.everitoken.sdk.java.params.ApiParams;
import io.everitoken.sdk.java.params.NetParams;
import org.json.JSONObject;

import javax.annotation.Nullable;

public class HeadBlockHeaderState extends ApiResource {
    private static final String name = "headBlockHeaderState";
    private static final String uri = "/v1/chain/get_head_block_header_state";
    private static final String method = "GET";

    public HeadBlockHeaderState() {
        super(name, uri, method);
    }

    @Override
    protected GetRequest buildRequest(NetParams netParams, @Nullable ApiParams apiParams) {
        return Unirest.get(getUrl(netParams));
    }

    public JSONObject get(NetParams netParams, @Nullable ApiParams apiParams) {
        ApiResponse<JsonNode> res = super.makeRequest(netParams, apiParams);
        return res.getPayload().getObject();
    }
}
