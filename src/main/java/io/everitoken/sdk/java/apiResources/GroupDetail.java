package io.everitoken.sdk.java.apiResources;

import com.mashape.unirest.http.JsonNode;
import io.everitoken.sdk.java.EvtSdkException;
import io.everitoken.sdk.java.model.DomainDetailData;
import io.everitoken.sdk.java.model.GroupDetailData;
import io.everitoken.sdk.java.params.RequestParams;
import org.json.JSONObject;

public class GroupDetail extends ApiResource {
    private static final String name = "groupDetail";
    private static final String uri = "/v1/evt/get_group";
    private static final String method = "POST";

    public GroupDetail() {
        super(name, uri, method);
    }

    public GroupDetailData request(RequestParams requestParams) throws EvtSdkException {
        JsonNode res = super.makeRequest(requestParams);
        return new GroupDetailData(res.getObject());
    }
}
