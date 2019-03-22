package io.everitoken.sdk.java.apiResource;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import com.mashape.unirest.http.JsonNode;

import org.jetbrains.annotations.NotNull;
import org.json.JSONObject;

import io.everitoken.sdk.java.dto.ActionData;
import io.everitoken.sdk.java.exceptions.ApiResponseException;
import io.everitoken.sdk.java.param.RequestParams;

public class FungibleAction extends ApiResource {
    private static final String uri = "/v1/history/get_fungible_actions";

    public FungibleAction() {
        super(uri);
    }

    public FungibleAction(@NotNull ApiRequestConfig apiRequestConfig) {
        super(uri, apiRequestConfig);
    }

    public List<ActionData> request(RequestParams requestParams) throws ApiResponseException {
        JsonNode res = super.makeRequest(requestParams);

        return StreamSupport.stream(res.getArray().spliterator(), true).map(raw -> ActionData.create((JSONObject) raw))
                .collect(Collectors.toList());
    }
}
