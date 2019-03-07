package io.everitoken.sdk.java.apiResource;

import com.mashape.unirest.http.JsonNode;
import io.everitoken.sdk.java.exceptions.ApiResponseException;
import io.everitoken.sdk.java.param.RequestParams;
import org.jetbrains.annotations.NotNull;
import org.json.JSONArray;

public class RequiredSuspendedKeys extends ApiResource {
    private static final String uri = "/v1/chain/get_suspend_required_keys";

    public RequiredSuspendedKeys() {
        super(uri);
    }

    public RequiredSuspendedKeys(@NotNull ApiRequestConfig apiRequestConfig) {
        super(uri, apiRequestConfig);
    }

    public JSONArray request(RequestParams requestParams) throws ApiResponseException {
        JsonNode res = super.makeRequest(requestParams);
        return res.getObject().getJSONArray("required_keys");
    }
}
