package io.everitoken.sdk.java.apiResources;

import com.mashape.unirest.http.JsonNode;
import io.everitoken.sdk.java.EvtSdkException;
import io.everitoken.sdk.java.model.GroupName;
import io.everitoken.sdk.java.params.RequestParams;
import org.json.JSONArray;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class HistoryGroup extends ApiResource {
    private static final String uri = "/v1/history/get_groups";
    private static final String method = "POST";

    public HistoryGroup() {
        super(uri, method);
    }

    public List<GroupName> request(RequestParams requestParams) throws EvtSdkException {
        JsonNode res = super.makeRequest(requestParams);
        JSONArray payload = res.getArray();

        return StreamSupport.stream(payload.spliterator(), false)
                .map((name) -> new GroupName((String) name))
                .collect(Collectors.toList());
    }
}
