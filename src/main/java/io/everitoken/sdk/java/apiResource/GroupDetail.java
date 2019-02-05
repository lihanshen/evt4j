package io.everitoken.sdk.java.apiResource;

import com.mashape.unirest.http.JsonNode;
import io.everitoken.sdk.java.dto.GroupDetailData;
import io.everitoken.sdk.java.exceptions.ApiResponseException;
import io.everitoken.sdk.java.param.RequestParams;

public class GroupDetail extends ApiResource {
    private static final String uri = "/v1/evt/get_group";

    public GroupDetail() {
        super(uri);
    }

    public GroupDetailData request(RequestParams requestParams) throws ApiResponseException {
        JsonNode res = super.makeRequest(requestParams);
        return GroupDetailData.create(res.getObject());
    }
}
