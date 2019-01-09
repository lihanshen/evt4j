package io.everitoken.sdk.java.apiResources;

import com.mashape.unirest.http.JsonNode;
import io.everitoken.sdk.java.EvtSdkException;
import io.everitoken.sdk.java.model.Action;
import io.everitoken.sdk.java.params.RequestParams;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class HistoryAction extends ApiResource {
    private static final String name = "historyAction";
    private static final String uri = "/v1/history/get_actions";
    private static final String method = "POST";

    public HistoryAction() {
        super(name, uri, method);
    }

    public List<Action> request(RequestParams requestParams) throws EvtSdkException {
        JsonNode res = super.makeRequest(requestParams);
        JSONArray payload = res.getArray();

        return StreamSupport.stream(payload.spliterator(), true)
                .map(raw -> new Action((JSONObject) raw))
                .collect(Collectors.toList());
    }
}
