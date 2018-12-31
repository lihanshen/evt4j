package io.everitoken.sdk.java.apiResources;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import io.everitoken.sdk.java.ApiResponse;
import io.everitoken.sdk.java.ErrorCode;
import io.everitoken.sdk.java.EvtSdkException;
import io.everitoken.sdk.java.model.DomainName;
import io.everitoken.sdk.java.params.NetParams;
import io.everitoken.sdk.java.params.PublicKeysParams;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class HistoryDomain extends ApiResource {
    private static final String name = "historyDomain";
    private static final String uri = "/v1/history/get_domains";
    private static final String method = "POST";

    public HistoryDomain() {
        super(name, uri, method);
    }

    public ApiResponse<List<DomainName>> get(NetParams netParams, PublicKeysParams publicKeysParams) {
        ApiResponse<List<DomainName>> res = new ApiResponse<>(null, null);
        try {

            // build the body and send along with the request
            JSONObject body = getBodyPayload(publicKeysParams);
            HttpResponse<JsonNode> json = Unirest.post(getUrl(netParams)).body(body).asJson();

            res.setPayload(parseResult(json));
        } catch (UnirestException ex) {
            // TODO error code with custom error message from server side
            res.setError(new EvtSdkException(null, ErrorCode.API_RESOURCE_FAILURE));
        }

        return res;
    }

    private JSONObject getBodyPayload(PublicKeysParams publicKeysParams) {
        JSONObject jsonObject = new JSONObject();
        JSONArray keys = new JSONArray(publicKeysParams.getPublicKeyAsStringList());
        jsonObject.put("keys", keys);

        return jsonObject;
    }

    private List<DomainName> parseResult(HttpResponse<JsonNode> json) {
        List<DomainName> domainNameList = new ArrayList<>();

        JSONArray domains = json.getBody().getArray();
        for (int i = 0; i < domains.length(); i++) {
            domainNameList.add(new DomainName(domains.getString(i)));
        }

        return domainNameList;
    }

}
