package io.everitoken.sdk.java;

import javax.annotation.Nullable;

// TODO write tests
public class ApiResponse<T> {
    private T payload;

    private EvtSdkException error;

    public ApiResponse(T payload, @Nullable EvtSdkException error) {
        this.payload = payload;
        this.error = error;
    }

    public boolean isError() {
        return error != null;
    }

    public EvtSdkException getError() {
        return error;
    }

    public T getPayload() {
        return payload;
    }

    public void setPayload(T payload) {
        this.payload = payload;
    }

    public void setError(EvtSdkException error) {
        this.error = error;
    }
}
