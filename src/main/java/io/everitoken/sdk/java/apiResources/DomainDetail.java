package io.everitoken.sdk.java.apiResources;

import com.mashape.unirest.http.JsonNode;
import io.everitoken.sdk.java.dto.DomainDetailData;
import io.everitoken.sdk.java.exceptions.ApiResponseException;
import io.everitoken.sdk.java.params.RequestParams;

public class DomainDetail extends ApiResource {
    private static final String uri = "/v1/evt/get_domain";
    private static final String method = "POST";

    public DomainDetail() {
        super(uri, method);
    }

    public DomainDetailData request(RequestParams requestParams) throws ApiResponseException {
        JsonNode res = super.makeRequest(requestParams);
        return DomainDetailData.of(res.getObject());
    }
}
