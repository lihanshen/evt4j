package io.everitoken.sdk.java.apiResource;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.mashape.unirest.http.JsonNode;

import org.jetbrains.annotations.NotNull;
import org.json.JSONArray;
import org.json.JSONObject;

import io.everitoken.sdk.java.dto.TokenDomain;
import io.everitoken.sdk.java.exceptions.ApiResponseException;
import io.everitoken.sdk.java.param.RequestParams;

public class HistoryToken extends ApiResource {
    private static final String uri = "/v1/history/get_tokens";

    public HistoryToken() {
        super(uri);
    }

    public HistoryToken(@NotNull ApiRequestConfig apiRequestConfig) {
        super(uri, apiRequestConfig);
    }

    public List<TokenDomain> request(RequestParams requestParams) throws ApiResponseException {
        JsonNode res = super.makeRequest(requestParams);
        JSONObject payload = res.getObject();

        List<TokenDomain> tokens = new ArrayList<>();

        if (payload == null) {
            return tokens;
        }

        Iterator<String> domains = payload.keys();

        while (domains.hasNext()) {
            String domainName = domains.next();
            JSONArray tokensInDomain = payload.getJSONArray(domainName);
            tokensInDomain.forEach(tokenInDomain -> tokens.add(new TokenDomain((String) tokenInDomain, domainName)));
        }

        return tokens;
    }
}
