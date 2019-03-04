package io.everitoken.sdk.java.apiResource;

import com.mashape.unirest.http.JsonNode;
import io.everitoken.sdk.java.exceptions.ApiResponseException;
import io.everitoken.sdk.java.param.RequestParams;

public class SuspendedProposal extends ApiResource {
    private static final String uri = "/v1/evt/get_suspend";

    public SuspendedProposal() {
        super(uri);
    }

    public String request(RequestParams requestParams) throws ApiResponseException {
        JsonNode res = super.makeRequest(requestParams);
        return res.toString();
    }
}
