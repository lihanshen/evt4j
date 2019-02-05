package io.everitoken.sdk.java.apiResource;

import io.everitoken.sdk.java.exceptions.ApiResponseException;
import io.everitoken.sdk.java.param.RequestParams;
import org.json.JSONArray;

public class TransactionIds extends ApiResource {
    private static final String uri = "/v1/chain/get_transaction_ids_for_block";

    public TransactionIds() {
        super(uri);
    }

    public JSONArray request(RequestParams requestParams) throws ApiResponseException {
        return super.makeRequest(requestParams).getArray();
    }
}
