package io.everitoken.sdk.java.apiResource;

import com.mashape.unirest.http.JsonNode;
import io.everitoken.sdk.java.dto.TokenDomain;
import io.everitoken.sdk.java.exceptions.ApiResponseException;
import io.everitoken.sdk.java.param.RequestParams;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class HistoryToken extends ApiResource {
    private static final String uri = "/v1/history/get_tokens";

    public HistoryToken() {
        super(uri);
    }

    /**
     * If no tokens are processed by the public keys, node will return `null`, this will be mapped in to empty array.
     * If tokens are returned from node, they are grouped by `domainName` as shown below:
     * ```
     * { "domain1": ["token1", "token2"] }
     * ```
     * It needs to be converted to List<TokenDomain> as shown below:
     * ```
     * [
     * { "name": "token1", "domain": "domain1" },
     * { "name": "token2", "domain": "domain1" }
     * ]
     * ```
     *
     * @param requestParams
     * @return List<TokenDomain>
     */
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
