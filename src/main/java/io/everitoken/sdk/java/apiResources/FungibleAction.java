package io.everitoken.sdk.java.apiResources;

import com.mashape.unirest.http.JsonNode;
import io.everitoken.sdk.java.dto.ActionData;
import io.everitoken.sdk.java.exceptions.ApiResponseException;
import io.everitoken.sdk.java.params.RequestParams;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class FungibleAction extends ApiResource {
    private static final String uri = "/v1/history/get_fungible_actions";
    private static final String method = "POST";

    public FungibleAction() {
        super(uri, method);
    }

    public List<ActionData> request(RequestParams requestParams) throws ApiResponseException {
        JsonNode res = super.makeRequest(requestParams);
        JSONArray payload = res.getArray();

        return StreamSupport.stream(payload.spliterator(), true)
                .map(raw -> ActionData.create((JSONObject) raw))
                .collect(Collectors.toList());
    }
}
