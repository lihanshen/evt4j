package io.everitoken.sdk.java.apiResources;

import com.mashape.unirest.http.JsonNode;
import io.everitoken.sdk.java.exceptions.ApiResponseException;
import io.everitoken.sdk.java.model.TransactionDetail;
import io.everitoken.sdk.java.params.RequestParams;

public class HistoryTransactionDetail extends ApiResource {
    private static final String uri = "/v1/history/get_transaction";
    private static final String method = "POST";

    public HistoryTransactionDetail(boolean useHistoryPlugin) {
        super(useHistoryPlugin ? uri : "/v1/chain/get_transaction", method);
    }

    public HistoryTransactionDetail() {
        super(uri, method);
    }

    public TransactionDetail request(RequestParams requestParams) throws ApiResponseException {
        JsonNode res = super.makeRequest(requestParams);
        return TransactionDetail.create(res.getObject());
    }
}
