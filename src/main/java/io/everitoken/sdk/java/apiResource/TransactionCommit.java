package io.everitoken.sdk.java.apiResource;

import com.mashape.unirest.http.JsonNode;

import org.jetbrains.annotations.NotNull;

import io.everitoken.sdk.java.dto.TransactionData;
import io.everitoken.sdk.java.exceptions.ApiResponseException;
import io.everitoken.sdk.java.param.RequestParams;

public class TransactionCommit extends ApiResource {
    private static final String uri = "/v1/chain/push_transaction";

    public TransactionCommit() {
        super(uri);
    }

    public TransactionCommit(@NotNull ApiRequestConfig apiRequestConfig) {
        super(uri, apiRequestConfig);
    }

    public TransactionData request(RequestParams requestParams) throws ApiResponseException {
        JsonNode res = super.makeRequest(requestParams);
        return TransactionData.ofRaw(res.getObject());
    }
}
