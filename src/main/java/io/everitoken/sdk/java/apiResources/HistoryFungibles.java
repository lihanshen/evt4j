package io.everitoken.sdk.java.apiResources;

import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.request.body.RequestBodyEntity;
import io.everitoken.sdk.java.ApiResponse;
import io.everitoken.sdk.java.params.ApiParams;
import io.everitoken.sdk.java.params.NetParams;
import org.json.JSONArray;
import org.json.JSONObject;

import javax.annotation.Nullable;
import java.util.Objects;

public class HistoryFungibles extends ApiResource {
    private static final String name = "historyFungibles";
    private static final String uri = "/v1/history/get_fungibles";
    private static final String method = "POST";
    private static final String IDS_KEY = "ids";

    public HistoryFungibles() {
        super(name, uri, method);
    }

    @Override
    public RequestBodyEntity buildRequest(NetParams netParams, @Nullable ApiParams apiParams) {
        Objects.requireNonNull(apiParams);
        return Unirest.post(getUrl(netParams)).body(apiParams.asJson());
    }

    public JSONObject get(NetParams netParams, ApiParams apiParams) {
        ApiResponse<JsonNode> res = super.makeRequest(netParams, apiParams);
        JSONArray payload = res.getPayload().getArray();

        JSONObject fungibleIds = new JSONObject();
        fungibleIds.put(IDS_KEY, payload);

        return fungibleIds;
    }
}
