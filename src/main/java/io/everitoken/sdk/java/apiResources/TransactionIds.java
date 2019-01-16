package io.everitoken.sdk.java.apiResources;

import io.everitoken.sdk.java.exceptions.ApiResponseException;
import io.everitoken.sdk.java.params.RequestParams;
import org.json.JSONArray;

public class TransactionIds extends ApiResource {
    private static final String uri = "/v1/chain/get_transaction_ids_for_block";
    private static final String method = "POST";

    public TransactionIds() {
        super(uri, method);
    }

    public JSONArray request(RequestParams requestParams) throws ApiResponseException {
        return super.makeRequest(requestParams).getArray();
    }
}
