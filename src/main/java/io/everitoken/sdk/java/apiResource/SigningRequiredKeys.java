package io.everitoken.sdk.java.apiResource;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import com.mashape.unirest.http.JsonNode;

import org.jetbrains.annotations.NotNull;
import org.json.JSONArray;

import io.everitoken.sdk.java.exceptions.ApiResponseException;
import io.everitoken.sdk.java.param.RequestParams;

public class SigningRequiredKeys extends ApiResource {
    private static final String uri = "/v1/chain/get_required_keys";

    public SigningRequiredKeys() {
        super(uri);
    }

    public SigningRequiredKeys(@NotNull ApiRequestConfig apiRequestConfig) {
        super(uri, apiRequestConfig);
    }

    public List<String> request(RequestParams requestParams) throws ApiResponseException {
        JsonNode res = super.makeRequest(requestParams);
        JSONArray array = res.getObject().getJSONArray("required_keys");

        return StreamSupport.stream(array.spliterator(), true).map(key -> (String) key).collect(Collectors.toList());
    }
}
