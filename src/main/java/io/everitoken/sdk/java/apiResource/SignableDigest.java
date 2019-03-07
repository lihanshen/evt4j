package io.everitoken.sdk.java.apiResource;

import com.mashape.unirest.http.JsonNode;
import io.everitoken.sdk.java.Utils;
import io.everitoken.sdk.java.exceptions.ApiResponseException;
import io.everitoken.sdk.java.param.RequestParams;
import org.jetbrains.annotations.NotNull;

public class SignableDigest extends ApiResource {
    private static final String uri = "/v1/chain/trx_json_to_digest";

    public SignableDigest() {
        super(uri);
    }

    public SignableDigest(@NotNull ApiRequestConfig apiRequestConfig) {
        super(uri, apiRequestConfig);
    }

    public byte[] request(RequestParams requestParams) throws ApiResponseException {
        JsonNode res = super.makeRequest(requestParams);
        return Utils.HEX.decode(res.getObject().getString("digest"));
    }
}
