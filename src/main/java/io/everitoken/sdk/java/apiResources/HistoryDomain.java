package io.everitoken.sdk.java.apiResources;

import com.mashape.unirest.http.JsonNode;
import io.everitoken.sdk.java.EvtSdkException;
import io.everitoken.sdk.java.model.NameableResource;
import io.everitoken.sdk.java.params.RequestParams;
import org.json.JSONArray;

import java.util.ArrayList;
import java.util.List;

public class HistoryDomain extends ApiResource {
    private static final String uri = "/v1/history/get_domains";
    private static final String method = "POST";

    public HistoryDomain() {
        super(uri, method);
    }

    public List<NameableResource> request(RequestParams requestParams) throws EvtSdkException {
        JsonNode res = super.makeRequest(requestParams);

        List<NameableResource> domainNameList = new ArrayList<>();
        JSONArray domains = res.getArray();

        for (int i = 0; i < domains.length(); i++) {
            domainNameList.add(NameableResource.create(domains.getString(i)));
        }

        return domainNameList;
    }
}
