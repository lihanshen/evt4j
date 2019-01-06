package io.everitoken.sdk.java.apiResources;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import com.mashape.unirest.request.BaseRequest;
import io.everitoken.sdk.java.ApiResponse;
import io.everitoken.sdk.java.ErrorCode;
import io.everitoken.sdk.java.EvtSdkException;
import io.everitoken.sdk.java.params.ApiParams;
import io.everitoken.sdk.java.params.NetParams;

import javax.annotation.Nullable;

public abstract class ApiResource {

    private String name;
    private String uri;
    private String method;

    protected ApiResource(String name, String uri, String method) {
        this.name = name;
        this.uri = uri;
        this.method = method;
    }

    protected BaseRequest buildRequest(NetParams netParams, @Nullable ApiParams apiParams) {
        return Unirest.get(getUrl(netParams));
    }

    public ApiResponse<JsonNode> makeRequest(NetParams netParams, @Nullable ApiParams apiParams) {
        ApiResponse<JsonNode> res = new ApiResponse<>(null, null);
        try {
            HttpResponse<JsonNode> json = buildRequest(netParams, apiParams).asJson();
            res.setPayload(json.getBody());
        } catch (UnirestException ex) {
            // TODO error code with custom error message from server side
            System.out.println(ex.getMessage());
            res.setError(new EvtSdkException(null, ErrorCode.API_RESOURCE_FAILURE));
        }

        return res;
    }

    private String getUri() {
        return uri;
    }

    private String getMethod() {
        return method;
    }

    public String getUrl(NetParams netParams) {
        return netParams.getEndpointUrl() + getUri();
    }

    public boolean equals(ApiResource obj) {
        String resourceName = getUri() + getMethod();
        return resourceName.equals(obj.getUri() + getMethod());
    }
}
