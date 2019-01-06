package io.everitoken.sdk.java.apiResources;

import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.request.body.RequestBodyEntity;
import io.everitoken.sdk.java.ApiResponse;
import io.everitoken.sdk.java.params.ApiParams;
import io.everitoken.sdk.java.params.NetParams;
import org.json.JSONArray;

import java.util.Objects;

public class TransactionIds extends ApiResource {
    private static final String name = "transactionIds";
    private static final String uri = "/v1/chain/get_transaction_ids_for_block";
    private static final String method = "POST";

    public TransactionIds() {
        super(name, uri, method);
    }

    @Override
    public RequestBodyEntity buildRequest(NetParams netParams, ApiParams apiParams) {
        Objects.requireNonNull(apiParams);
        return Unirest.post(getUrl(netParams)).body(apiParams.asJson());
    }

    public JSONArray get(NetParams netParams, ApiParams apiParams) {
        ApiResponse<JsonNode> res = super.makeRequest(netParams, apiParams);
        JSONArray payload = res.getPayload().getArray();

        return payload;
    }
}
