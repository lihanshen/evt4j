package io.everitoken.sdk.java.apiResources;

import com.mashape.unirest.http.JsonNode;
import io.everitoken.sdk.java.dto.FungibleBalanceData;
import io.everitoken.sdk.java.exceptions.ApiResponseException;
import io.everitoken.sdk.java.params.RequestParams;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class FungibleBalance extends ApiResource {
    private static final String uri = "/v1/evt/get_fungible_balance";

    public FungibleBalance() {
        super(uri);
    }

    public List<FungibleBalanceData> request(RequestParams requestParams) throws ApiResponseException {
        JsonNode res = super.makeRequest(requestParams);
        System.out.println(res);
        return StreamSupport.stream(res.getArray().spliterator(), true)
                .map(balance -> new FungibleBalanceData((String) balance))
                .collect(Collectors.toList());
    }
}
