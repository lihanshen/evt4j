package io.everitoken.sdk.java.apiResource;

import com.mashape.unirest.http.JsonNode;
import io.everitoken.sdk.java.exceptions.ApiResponseException;
import io.everitoken.sdk.java.param.RequestParams;
import org.apache.http.conn.HttpHostConnectException;

public class EvtLinkStatus extends ApiResource {

    public EvtLinkStatus(boolean block) {
        super(block ? "/v1/evt_link/get_trx_id_for_link_id" : "/v1/chain/get_trx_id_for_link_id");
    }

    public JsonNode request(RequestParams requestParams) throws ApiResponseException, HttpHostConnectException {
        return super.makeRequest(requestParams);
    }
}
