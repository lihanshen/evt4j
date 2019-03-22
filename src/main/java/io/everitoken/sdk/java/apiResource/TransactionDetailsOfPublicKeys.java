package io.everitoken.sdk.java.apiResource;

import org.jetbrains.annotations.NotNull;
import org.json.JSONArray;

import io.everitoken.sdk.java.exceptions.ApiResponseException;
import io.everitoken.sdk.java.param.RequestParams;

public class TransactionDetailsOfPublicKeys extends ApiResource {
    private static final String uri = "/v1/history/get_transactions";

    public TransactionDetailsOfPublicKeys() {
        super(uri);
    }

    public TransactionDetailsOfPublicKeys(@NotNull ApiRequestConfig apiRequestConfig) {
        super(uri, apiRequestConfig);
    }

    public JSONArray request(RequestParams requestParams) throws ApiResponseException {
        return super.makeRequest(requestParams).getArray();
    }
}
