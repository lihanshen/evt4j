package io.everitoken.sdk.java.apiResources;

import com.mashape.unirest.http.JsonNode;
import io.everitoken.sdk.java.EvtSdkException;
import io.everitoken.sdk.java.model.TokenDetailData;
import io.everitoken.sdk.java.params.RequestParams;

public class TokenDetail extends ApiResource {
    private static final String uri = "/v1/evt/get_token";
    private static final String method = "POST";

    public TokenDetail() {
        super(uri, method);
    }

    public TokenDetailData request(RequestParams requestParams) throws EvtSdkException {
        JsonNode res = super.makeRequest(requestParams);
        return new TokenDetailData(res.getObject());
    }
}
