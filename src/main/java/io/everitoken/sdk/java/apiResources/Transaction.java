package io.everitoken.sdk.java.apiResources;

import com.mashape.unirest.http.JsonNode;
import io.everitoken.sdk.java.PublicKey;
import io.everitoken.sdk.java.exceptions.ApiResponseException;
import io.everitoken.sdk.java.params.RequestParams;
import org.json.JSONObject;

public class Transaction extends ApiResource {
    private static final String uri = "/v1/chain/push_transaction";

    private final TransctionConfiguration configuration;

    public Transaction(int maxCharge, PublicKey payer) {
        super(uri);
        configuration = TransctionConfiguration.defaultConfig(maxCharge, payer);
    }

    public Transaction(TransctionConfiguration configuration) {
        super(uri);
        this.configuration = configuration;
    }

    public JSONObject request(RequestParams requestParams) throws ApiResponseException {
        JsonNode res = super.makeRequest(requestParams);
        return res.getObject();
    }
}
