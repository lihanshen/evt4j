package io.everitoken.sdk.java;

import com.mashape.unirest.http.JsonNode;
import io.everitoken.sdk.java.apiResources.HeadBlockHeaderState;
import io.everitoken.sdk.java.apiResources.HistoryDomain;
import io.everitoken.sdk.java.apiResources.HistoryToken;
import io.everitoken.sdk.java.apiResources.Info;
import io.everitoken.sdk.java.keyProvider.KeyProvider;
import io.everitoken.sdk.java.params.NetParams;
import io.everitoken.sdk.java.params.PublicKeysParams;
import io.everitoken.sdk.java.params.TestNetNetParams;

import javax.annotation.Nullable;

public class Api {
    private NetParams netParams;
    private KeyProvider keyProvider;

    public Api(NetParams netParams, @Nullable KeyProvider keyProvider) {
        this.netParams = netParams;
        this.keyProvider = keyProvider;
    }

    public Api() {
        this(new TestNetNetParams(), null);
    }

    public ApiResponse<JsonNode> getInfo() throws EvtSdkException {
        Info info = new Info();
        return info.get(netParams, null);
    }

    public ApiResponse<JsonNode> getHeadBlockHeaderState() throws EvtSdkException {
        HeadBlockHeaderState headBlockHeaderState = new HeadBlockHeaderState();
        return headBlockHeaderState.get(netParams, null);
    }

    public ApiResponse<JsonNode> getCreatedDomains(PublicKeysParams publicKeysParams) throws EvtSdkException {
        HistoryDomain historyDomain = new HistoryDomain();
        return historyDomain.get(netParams, publicKeysParams);
    }

    public ApiResponse<JsonNode> getOwnedTokens(PublicKeysParams publicKeysParams) throws EvtSdkException {
        HistoryToken historyToken = new HistoryToken();
        return historyToken.get(netParams, publicKeysParams);
    }
}
