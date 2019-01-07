package io.everitoken.sdk.java.apiResources;

import com.mashape.unirest.http.JsonNode;
import io.everitoken.sdk.java.ApiResponse;
import io.everitoken.sdk.java.model.GroupName;
import io.everitoken.sdk.java.params.ApiParams;
import io.everitoken.sdk.java.params.NetParams;
import org.json.JSONArray;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class HistoryGroup extends ApiResource {
    private static final String name = "historyGroup";
    private static final String uri = "/v1/history/get_groups";
    private static final String method = "POST";

    public HistoryGroup() {
        super(name, uri, method);
    }

    public List<GroupName> get(NetParams netParams, ApiParams apiParams) {
        ApiResponse<JsonNode> res = super.makeRequest(netParams, apiParams);
        JSONArray payload = res.getPayload().getArray();

        return StreamSupport.stream(payload.spliterator(), false)
                .map((name) -> new GroupName((String) name))
                .collect(Collectors.toList());
    }
}
