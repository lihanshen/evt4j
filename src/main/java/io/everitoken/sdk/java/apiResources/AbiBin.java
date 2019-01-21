package io.everitoken.sdk.java.apiResources;

import com.mashape.unirest.http.JsonNode;
import io.everitoken.sdk.java.exceptions.ApiResponseException;
import io.everitoken.sdk.java.params.RequestParams;
import org.json.JSONObject;

public class AbiBin extends ApiResource {
    private static final String uri = "/v1/chain/abi_json_to_bin";
    private static final String method = "POST";

    public AbiBin() {
        super(uri, method);
    }

    public JSONObject request(RequestParams requestParams) throws ApiResponseException {
        JsonNode res = super.makeRequest(requestParams);
        JSONObject json = res.getObject();

        if (!json.has("binargs")) {
            throw new ApiResponseException("Abi to bin response should have a 'binargs' field", json);
        }

        return res.getObject();
    }
}
