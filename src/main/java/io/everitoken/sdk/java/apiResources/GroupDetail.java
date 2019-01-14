package io.everitoken.sdk.java.apiResources;

import com.mashape.unirest.http.JsonNode;
import io.everitoken.sdk.java.EvtSdkException;
import io.everitoken.sdk.java.model.GroupDetailData;
import io.everitoken.sdk.java.params.RequestParams;

public class GroupDetail extends ApiResource {
    private static final String uri = "/v1/evt/get_group";
    private static final String method = "POST";

    public GroupDetail() {
        super(uri, method);
    }

    public GroupDetailData request(RequestParams requestParams) throws EvtSdkException {
        JsonNode res = super.makeRequest(requestParams);
        return new GroupDetailData(res.getObject());
    }
}
