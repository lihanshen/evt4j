package io.everitoken.sdk.java.apiResources;

import com.mashape.unirest.http.JsonNode;
import io.everitoken.sdk.java.ApiResponse;
import io.everitoken.sdk.java.params.RequestParams;
import org.json.JSONArray;

public class TransactionIds extends ApiResource {
    private static final String name = "transactionIds";
    private static final String uri = "/v1/chain/get_transaction_ids_for_block";
    private static final String method = "POST";

    public TransactionIds() {
        super(name, uri, method);
    }

    public JSONArray get(RequestParams requestParams) {
        ApiResponse<JsonNode> res = super.makeRequest(requestParams);
        JSONArray payload = res.getPayload().getArray();

        return payload;
    }
}
