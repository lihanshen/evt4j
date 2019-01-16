package io.everitoken.sdk.java.exceptions;

import org.json.JSONObject;

public class ApiResponseException extends Exception implements EvtException {
    private JSONObject raw;

    public ApiResponseException(String message, Throwable cause) {
        super(message, cause);
    }

    public ApiResponseException(String message, JSONObject raw) {
        super(message);
        this.raw = raw;
    }

    public JSONObject getRaw() {
        return raw;
    }
}
