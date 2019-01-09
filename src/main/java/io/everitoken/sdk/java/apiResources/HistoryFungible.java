package io.everitoken.sdk.java.apiResources;

import com.mashape.unirest.http.JsonNode;
import io.everitoken.sdk.java.ApiResponse;
import io.everitoken.sdk.java.params.RequestParams;
import org.json.JSONArray;
import org.json.JSONObject;

public class HistoryFungible extends ApiResource {
    private static final String name = "historyFungibles";
    private static final String uri = "/v1/history/get_fungibles";
    private static final String method = "POST";
    private static final String IDS_KEY = "ids";

    public HistoryFungible() {
        super(name, uri, method);
    }

    public JSONObject get(RequestParams requestParams) {
        ApiResponse<JsonNode> res = super.makeRequest(requestParams);
        JSONArray payload = res.getPayload().getArray();

        JSONObject fungibleIds = new JSONObject();
        fungibleIds.put(IDS_KEY, payload);

        return fungibleIds;
    }
}
