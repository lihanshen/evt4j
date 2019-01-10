package io.everitoken.sdk.java.apiResources;

import com.mashape.unirest.http.JsonNode;
import io.everitoken.sdk.java.EvtSdkException;
import io.everitoken.sdk.java.model.DomainDetailData;
import io.everitoken.sdk.java.model.TokenDetailData;
import io.everitoken.sdk.java.params.RequestParams;
import org.json.JSONObject;

public class DomainDetail extends ApiResource {
    private static final String name = "domainDetail";
    private static final String uri = "/v1/evt/get_domain";
    private static final String method = "POST";

    public DomainDetail() {
        super(name, uri, method);
    }

    public DomainDetailData request(RequestParams requestParams) throws EvtSdkException {
        JsonNode res = super.makeRequest(requestParams);
        return new DomainDetailData(res.getObject());
    }
}
