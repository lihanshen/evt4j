package io.everitoken.sdk.java.apiResources;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import com.mashape.unirest.request.BaseRequest;
import io.everitoken.sdk.java.ApiResponse;
import io.everitoken.sdk.java.ErrorCode;
import io.everitoken.sdk.java.EvtSdkException;
import io.everitoken.sdk.java.params.NetParams;
import io.everitoken.sdk.java.params.RequestParams;

public abstract class ApiResource {

    private String name;
    private String uri;
    private String method;

    protected ApiResource(String name, String uri, String method) {
        this.name = name;
        this.uri = uri;
        this.method = method;
    }

    protected BaseRequest buildRequest(RequestParams requestParams) {
        return Unirest.post(getUrl(requestParams.getNetParams())).body(requestParams.getApiParams().asJson());
    }

    public ApiResponse<JsonNode> makeRequest(RequestParams requestParams) {
        ApiResponse<JsonNode> res = new ApiResponse<>(null, null);
        try {
            HttpResponse<JsonNode> json = buildRequest(requestParams).asJson();
            res.setPayload(json.getBody());
        } catch (UnirestException ex) {
            // TODO error code with custom error message from server side
            System.out.println(String.format("%s: %s", "Error in ApiResource class", ex.getMessage()));
            res.setError(new EvtSdkException(null, ErrorCode.API_RESPONSE_FAILURE, ex.getMessage()));
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
