package io.everitoken.sdk.java.apiResources;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import io.everitoken.sdk.java.ApiResponse;
import io.everitoken.sdk.java.ErrorCode;
import io.everitoken.sdk.java.EvtSdkException;
import io.everitoken.sdk.java.params.NetParams;

// TODO write tests
public abstract class ApiResource {

    private String name;
    private String uri;
    private String method;

    protected ApiResource(String name, String uri, String method) {
        this.name = name;
        this.uri = uri;
        this.method = method;
    }

    public ApiResponse get(NetParams netParams) {
        ApiResponse res = new ApiResponse(null, null);
        try {
            HttpResponse<JsonNode> json = Unirest.get(getUrl(netParams)).asJson();
            res.setPayload(json);
        } catch (UnirestException ex) {
            // TODO error code with custom error message from server side
            res.setError(new EvtSdkException(null, ErrorCode.API_RESOURCE_FAILURE));
        }

        return res;
    }

    public String getName() {
        return name;
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
