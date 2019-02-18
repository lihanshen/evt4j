package io.everitoken.sdk.java.apiResource;

import com.mashape.unirest.http.JsonNode;
import io.everitoken.sdk.java.Asset;
import io.everitoken.sdk.java.exceptions.ApiResponseException;
import io.everitoken.sdk.java.param.RequestParams;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class FungibleBalance extends ApiResource {
    private static final String uri = "/v1/history/get_fungibles_balance";

    public FungibleBalance() {
        super(uri);
    }

    public List<Asset> request(RequestParams requestParams) throws ApiResponseException {
        JsonNode res = super.makeRequest(requestParams);
        return StreamSupport.stream(res.getArray().spliterator(), true)
                .map(balance -> Asset.parseFromRawBalance((String) balance))
                .collect(Collectors.toList());
    }
}
