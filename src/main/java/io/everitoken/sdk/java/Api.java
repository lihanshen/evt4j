package io.everitoken.sdk.java;

import io.everitoken.sdk.java.apiResources.HeadBlockHeaderState;
import io.everitoken.sdk.java.apiResources.HistoryDomain;
import io.everitoken.sdk.java.apiResources.HistoryToken;
import io.everitoken.sdk.java.apiResources.Info;
import io.everitoken.sdk.java.keyProvider.KeyProvider;
import io.everitoken.sdk.java.model.DomainName;
import io.everitoken.sdk.java.params.NetParams;
import io.everitoken.sdk.java.params.PublicKeysParams;
import io.everitoken.sdk.java.params.TestNetNetParams;
import org.json.JSONObject;

import javax.annotation.Nullable;
import java.util.List;

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

    public JSONObject getInfo() throws EvtSdkException {
        Info info = new Info();
        return info.get(netParams, null);
    }

    public JSONObject getHeadBlockHeaderState() throws EvtSdkException {
        HeadBlockHeaderState headBlockHeaderState = new HeadBlockHeaderState();
        return headBlockHeaderState.get(netParams, null);
    }


    public List<DomainName> getCreatedDomains(PublicKeysParams publicKeysParams) throws EvtSdkException {
        HistoryDomain historyDomain = new HistoryDomain();
        return historyDomain.get(netParams, publicKeysParams);
    }

    public JSONObject getOwnedTokens(PublicKeysParams publicKeysParams) throws EvtSdkException {
        HistoryToken historyToken = new HistoryToken();
        return historyToken.get(netParams, publicKeysParams);
    }
   
    public static void main(String[] args) {
        Api api = new Api();

        try {
            PublicKeysParams publicKeysParams = new PublicKeysParams(new String[]{
                    "EVT8aNw4NTvjBL1XR6hgy4zcA9jzh1JLjMuAw85mSbW68vYzw2f9H"
            });
            JSONObject res = api.getOwnedTokens(publicKeysParams);
            System.out.println(res);
        } catch (Exception ex) {
            System.out.println("error");
        }
    }
}
