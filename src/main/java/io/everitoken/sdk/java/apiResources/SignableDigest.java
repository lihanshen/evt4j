package io.everitoken.sdk.java.apiResources;

import com.mashape.unirest.http.JsonNode;
import io.everitoken.sdk.java.Utils;
import io.everitoken.sdk.java.exceptions.ApiResponseException;
import io.everitoken.sdk.java.params.RequestParams;

public class SignableDigest extends ApiResource {
    private static final String uri = "/v1/chain/trx_json_to_digest";

    public SignableDigest() {
        super(uri);
    }

    public byte[] request(RequestParams requestParams) throws ApiResponseException {
        JsonNode res = super.makeRequest(requestParams);
        return Utils.HEX.decode(res.getObject().getString("digest"));
    }
}
