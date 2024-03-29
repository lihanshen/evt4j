package io.everitoken.sdk.java.apiResource;

import com.mashape.unirest.http.JsonNode;

import org.jetbrains.annotations.NotNull;

import io.everitoken.sdk.java.dto.TransactionDetail;
import io.everitoken.sdk.java.exceptions.ApiResponseException;
import io.everitoken.sdk.java.param.RequestParams;

public class HistoryTransactionDetail extends ApiResource {
    private static final String uri = "/v1/history/get_transaction";

    public HistoryTransactionDetail(boolean useHistoryPlugin) {
        super(useHistoryPlugin ? uri : "/v1/chain/get_transaction");
    }

    public HistoryTransactionDetail() {
        super(uri);
    }

    public HistoryTransactionDetail(@NotNull ApiRequestConfig apiRequestConfig) {
        super(uri, apiRequestConfig);
    }

    public TransactionDetail request(RequestParams requestParams) throws ApiResponseException {
        JsonNode res = super.makeRequest(requestParams);
        return TransactionDetail.create(res.getObject());
    }
}
