package io.everitoken.sdk.java.apiResources;

import com.alibaba.fastjson.JSON;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.request.GetRequest;
import io.everitoken.sdk.java.dto.NodeInfo;
import io.everitoken.sdk.java.exceptions.ApiResponseException;
import io.everitoken.sdk.java.params.RequestParams;

public class Info extends ApiResource {
    private static final String uri = "/v1/chain/get_info";
    private static final String method = "GET";

    public Info() {
        super(uri, method);
    }

    @Override
    protected GetRequest buildRequest(RequestParams requestParams) {
        return Unirest.get(getUrl(requestParams.getNetParams()));
    }

    public NodeInfo request(RequestParams requestParams) throws ApiResponseException {
        JsonNode res = super.makeRequest(requestParams);
        return JSON.parseObject(res.getObject().toString(), NodeInfo.class);
    }
}
