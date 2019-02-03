package io.everitoken.sdk.java.apiResources;

import com.mashape.unirest.http.JsonNode;
import io.everitoken.sdk.java.dto.Charge;
import io.everitoken.sdk.java.exceptions.ApiResponseException;
import io.everitoken.sdk.java.params.RequestParams;

public class TransactionEstimatedCharge extends ApiResource {
    private static final String uri = "/v1/chain/get_charge";

    public TransactionEstimatedCharge() {
        super(uri);
    }

    public Charge request(RequestParams requestParams) throws ApiResponseException {
        JsonNode res = super.makeRequest(requestParams);
        return Charge.ofRaw(res.getObject());
    }
}
