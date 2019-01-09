package io.everitoken.sdk.java.apiResources;

import com.mashape.unirest.http.JsonNode;
import io.everitoken.sdk.java.EvtSdkException;
import io.everitoken.sdk.java.model.TransactionDetail;
import io.everitoken.sdk.java.params.RequestParams;

public class HistoryTransactionDetail extends ApiResource {
    private static final String name = "transactionIds";
    private static final String uri = "/v1/history/get_transaction";
    private static final String method = "POST";

    public HistoryTransactionDetail(boolean useHistoryPlugin) {
        super(name, useHistoryPlugin ? uri : "/v1/chain/get_transaction", method);
    }

    public HistoryTransactionDetail() {
        super(name, uri, method);
    }

    public TransactionDetail request(RequestParams requestParams) throws EvtSdkException {
        JsonNode res = super.makeRequest(requestParams);
        return new TransactionDetail(res.getObject());
    }
}
