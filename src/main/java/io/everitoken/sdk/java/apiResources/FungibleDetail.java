package io.everitoken.sdk.java.apiResources;

import com.mashape.unirest.http.JsonNode;
import io.everitoken.sdk.java.dto.FungibleDetailData;
import io.everitoken.sdk.java.exceptions.ApiResponseException;
import io.everitoken.sdk.java.params.RequestParams;

public class FungibleDetail extends ApiResource {
    private static final String uri = "/v1/evt/get_fungible";

    public FungibleDetail() {
        super(uri);
    }

    public FungibleDetailData request(RequestParams requestParams) throws ApiResponseException {
        JsonNode res = super.makeRequest(requestParams);
        return FungibleDetailData.ofRaw(res.getObject());
    }
}
