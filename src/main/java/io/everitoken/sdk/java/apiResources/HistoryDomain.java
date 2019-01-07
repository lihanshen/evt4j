package io.everitoken.sdk.java.apiResources;

import com.mashape.unirest.http.JsonNode;
import io.everitoken.sdk.java.ApiResponse;
import io.everitoken.sdk.java.model.DomainName;
import io.everitoken.sdk.java.params.ApiParams;
import io.everitoken.sdk.java.params.NetParams;
import org.json.JSONArray;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

public class HistoryDomain extends ApiResource {
    private static final String name = "historyDomain";
    private static final String uri = "/v1/history/get_domains";
    private static final String method = "POST";

    public HistoryDomain() {
        super(name, uri, method);
    }

    public List<DomainName> get(NetParams netParams, @Nullable ApiParams apiParams) {
        ApiResponse<JsonNode> res = super.makeRequest(netParams, apiParams);

        List<DomainName> domainNameList = new ArrayList<>();
        JSONArray domains = res.getPayload().getArray();

        for (int i = 0; i < domains.length(); i++) {
            domainNameList.add(new DomainName(domains.getString(i)));
        }

        return domainNameList;
    }
}
