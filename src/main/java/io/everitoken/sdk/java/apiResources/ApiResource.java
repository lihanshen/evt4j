package io.everitoken.sdk.java.apiResources;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import com.mashape.unirest.request.BaseRequest;
import io.everitoken.sdk.java.exceptions.ApiResponseException;
import io.everitoken.sdk.java.params.NetParams;
import io.everitoken.sdk.java.params.RequestParams;
import org.json.JSONObject;

public abstract class ApiResource {
    private String uri;
    private String method;

    protected ApiResource(String uri, String method) {
        this.uri = uri;
        this.method = method;
    }

    protected BaseRequest buildRequest(RequestParams requestParams) {
        return Unirest.post(getUrl(requestParams.getNetParams())).body(requestParams.getApiParams().asJson());
    }

    public JsonNode makeRequest(RequestParams requestParams) throws ApiResponseException {
        try {
            HttpResponse<JsonNode> json = buildRequest(requestParams).asJson();
            JsonNode body = json.getBody();
            checkResponseError(body);
            return body;
        } catch (UnirestException ex) {
            throw new ApiResponseException(ex.getMessage(), ex);
        }
    }

    private void checkResponseError(JsonNode raw) throws ApiResponseException {
        JSONObject res = raw.getObject();
        if (!raw.isArray() && res.has("error")) {
            throw new ApiResponseException(String.format("Response Error for '%s'", uri), res);
        }
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
