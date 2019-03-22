package io.everitoken.sdk.java.apiResource;

import com.mashape.unirest.http.JsonNode;

import org.jetbrains.annotations.NotNull;

import io.everitoken.sdk.java.dto.TokenDetailData;
import io.everitoken.sdk.java.exceptions.ApiResponseException;
import io.everitoken.sdk.java.param.RequestParams;

public class TokenDetail extends ApiResource {
    private static final String uri = "/v1/evt/get_token";

    public TokenDetail() {
        super(uri);
    }

    public TokenDetail(@NotNull ApiRequestConfig apiRequestConfig) {
        super(uri, apiRequestConfig);
    }

    public TokenDetailData request(RequestParams requestParams) throws ApiResponseException {
        JsonNode res = super.makeRequest(requestParams);
        return TokenDetailData.create(res.getObject());
    }
}
