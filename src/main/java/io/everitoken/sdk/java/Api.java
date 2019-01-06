package io.everitoken.sdk.java;

import io.everitoken.sdk.java.apiResources.*;
import io.everitoken.sdk.java.keyProvider.KeyProvider;
import io.everitoken.sdk.java.model.Action;
import io.everitoken.sdk.java.model.DomainName;
import io.everitoken.sdk.java.model.GroupName;
import io.everitoken.sdk.java.model.TokenName;
import io.everitoken.sdk.java.params.*;
import org.json.JSONArray;
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

    public List<TokenName> getOwnedTokens(PublicKeysParams publicKeysParams) throws EvtSdkException {
        HistoryToken historyToken = new HistoryToken();
        return historyToken.get(netParams, publicKeysParams);
    }

    public List<GroupName> getManagedGroups(PublicKeysParams publicKeysParams) throws EvtSdkException {
        HistoryGroup historyGroup = new HistoryGroup();
        return historyGroup.get(netParams, publicKeysParams);
    }

    public JSONObject getCreatedFungibles(PublicKeysParams publicKeysParams) throws EvtSdkException {
        HistoryFungibles historyFungibles = new HistoryFungibles();
        return historyFungibles.get(netParams, publicKeysParams);
    }

    public List<Action> getActions(ActionParams actionParams) throws EvtSdkException {
        HistoryAction historyAction = new HistoryAction();
        return historyAction.get(netParams, actionParams);
    }

    public JSONArray getTransactionIdsInBlock(TextIdParams idParams) throws EvtSdkException {
        TransactionIds transactionIds = new TransactionIds();
        return transactionIds.get(netParams, idParams);
    }

    public static void main(String[] args) {
        Api api = new Api();
        // evtjava -> EVT8aNw4NTvjBL1XR6hgy4zcA9jzh1JLjMuAw85mSbW68vYzw2f9H
        //evtjs -> EVT6Qz3wuRjyN6gaU3P3XRxpnEZnM4oPxortemaWDwFRvsv2FxgND
        try {
//            PublicKeysParams publicKeysParams = new PublicKeysParams(new String[]{
//                    "EVT6Qz3wuRjyN6gaU3P3XRxpnEZnM4oPxortemaWDwFRvsv2FxgND"
//            });
//            JSONObject res = api.getHeadBlockHeaderState();
//            System.out.println(res);

//            List<DomainName> res = api.getCreatedDomains(publicKeysParams);
//            res.stream().forEach(domain -> System.out.println(domain.toString()));

//            List<TokenName> res = api.getOwnedTokens(publicKeysParams);
//            res.stream().forEach(token -> System.out.println(token.toString()));

//            List<GroupName> res = api.getManagedGroups(publicKeysParams);
//            res.stream().forEach(group -> System.out.println(group.toString()));

//            JSONObject res = api.getCreatedFungibles(publicKeysParams);
//            System.out.println(res);

//            ActionParams actionParams = new ActionParams("testdomainfei1");
//            List<Action> res = api.getActions(actionParams);
//            System.out.println(res);

//            JSONObject headerState = api.getHeadBlockHeaderState();
//            System.out.println(headerState.getString("id"));
//            JSONArray res = api.getTransactionIdsInBlock(new TextIdParams(headerState.getString("id")));
//            System.out.println(res);

        } catch (Exception ex) {
            System.out.println("error");
            System.out.println(ex.getStackTrace());
        }
    }
}
