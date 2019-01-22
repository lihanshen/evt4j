package io.everitoken.sdk.java.apiResources;

import com.mashape.unirest.http.JsonNode;
import io.everitoken.sdk.java.dto.FungibleCreated;
import io.everitoken.sdk.java.exceptions.ApiResponseException;
import io.everitoken.sdk.java.params.RequestParams;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class HistoryFungible extends ApiResource {
    private static final String uri = "/v1/history/get_fungibles";

    public HistoryFungible() {
        super(uri);
    }

    public FungibleCreated request(RequestParams requestParams) throws ApiResponseException {
        JsonNode res = super.makeRequest(requestParams);

        List<Integer> ids = StreamSupport.stream(res.getArray().spliterator(), true)
                .map(id -> (int) id).collect(Collectors.toList());

        return new FungibleCreated(ids);
    }
}
