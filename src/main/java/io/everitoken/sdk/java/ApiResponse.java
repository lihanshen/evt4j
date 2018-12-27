package io.everitoken.sdk.java;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;

import javax.annotation.Nullable;

// TODO write tests
public class ApiResponse {
    private HttpResponse<JsonNode> payload;

    private EvtSdkException error;

    public ApiResponse(HttpResponse<JsonNode> payload, @Nullable EvtSdkException error) {
        this.payload = payload;
        this.error = error;
    }

    public boolean isError() {
        return error != null;
    }

    public EvtSdkException getError() {
        return error;
    }

    public HttpResponse<JsonNode> getPayload() {
        return payload;
    }

    public void setPayload(HttpResponse<JsonNode> payload) {
        this.payload = payload;
    }

    public void setError(EvtSdkException error) {
        this.error = error;
    }
}
