package io.everitoken.sdk.java.apiResources;

import com.mashape.unirest.http.JsonNode;
import io.everitoken.sdk.java.exceptions.ApiResponseException;
import io.everitoken.sdk.java.params.RequestParams;
import org.json.JSONArray;
import org.json.JSONObject;

public class HistoryFungible extends ApiResource {
    private static final String uri = "/v1/history/get_fungibles";
    private static final String IDS_KEY = "ids";

    public HistoryFungible() {
        super(uri);
    }

    public JSONObject request(RequestParams requestParams) throws ApiResponseException {
        JsonNode res = super.makeRequest(requestParams);
        JSONArray payload = res.getArray();

        JSONObject fungibleIds = new JSONObject();
        fungibleIds.put(IDS_KEY, payload);

        return fungibleIds;
    }
}
