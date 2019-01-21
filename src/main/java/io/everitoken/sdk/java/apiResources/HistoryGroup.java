package io.everitoken.sdk.java.apiResources;

import com.mashape.unirest.http.JsonNode;
import io.everitoken.sdk.java.dto.NameableResource;
import io.everitoken.sdk.java.exceptions.ApiResponseException;
import io.everitoken.sdk.java.params.RequestParams;
import org.json.JSONArray;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class HistoryGroup extends ApiResource {
    private static final String uri = "/v1/history/get_groups";

    public HistoryGroup() {
        super(uri);
    }

    public List<NameableResource> request(RequestParams requestParams) throws ApiResponseException {
        JsonNode res = super.makeRequest(requestParams);
        JSONArray payload = res.getArray();

        return StreamSupport.stream(payload.spliterator(), false)
                .map((name) -> NameableResource.create((String) name))
                .collect(Collectors.toList());
    }
}
