package io.everitoken.sdk.java.apiResources;

import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.request.body.RequestBodyEntity;
import io.everitoken.sdk.java.ApiResponse;
import io.everitoken.sdk.java.model.TokenName;
import io.everitoken.sdk.java.params.ApiParams;
import io.everitoken.sdk.java.params.NetParams;
import org.json.JSONArray;
import org.json.JSONObject;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

public class HistoryToken extends ApiResource {
    private static final String name = "historyToken";
    private static final String uri = "/v1/history/get_tokens";
    private static final String method = "POST";

    public HistoryToken() {
        super(name, uri, method);
    }

    @Override
    public RequestBodyEntity buildRequest(NetParams netParams, @Nullable ApiParams apiParams) {
        Objects.requireNonNull(apiParams);
        return Unirest.post(getUrl(netParams)).body(apiParams.asJson());
    }

    /**
     * If no tokens are processed by the public keys, node will return `null`, this will be mapped in to empty array.
     * If tokens are returned from node, they are grouped by `domainName` as shown below:
     * ```
     * { "domain1": ["token1", "token2"] }
     * ```
     * It needs to be converted to List<TokenName> as shown below:
     * ```
     * [
     * { "name": "token1", "domain": "domain1" },
     * { "name": "token2", "domain": "domain1" }
     * ]
     * ```
     *
     * @param netParams
     * @param apiParams
     * @return List<TokenName>
     */
    public List<TokenName> get(NetParams netParams, ApiParams apiParams) {
        ApiResponse<JsonNode> res = super.makeRequest(netParams, apiParams);
        JSONObject payload = res.getPayload().getObject();

        List<TokenName> tokens = new ArrayList<>();

        if (payload == null) {
            return tokens;
        }

        Iterator<String> domains = payload.keys();

        while (domains.hasNext()) {
            String domainName = domains.next();
            JSONArray tokensInDomain = payload.getJSONArray(domainName);
            tokensInDomain.forEach(tokenInDomain -> tokens.add(new TokenName((String) tokenInDomain, domainName)));
        }

        return tokens;
    }
}
