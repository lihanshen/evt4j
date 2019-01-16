package io.everitoken.sdk.java;

import io.everitoken.sdk.java.apiResources.*;
import io.everitoken.sdk.java.exceptions.ApiResponseException;
import io.everitoken.sdk.java.keyProvider.KeyProvider;
import io.everitoken.sdk.java.model.*;
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

    public static void main(String[] args) {
        Api api = new Api();
        // evtjava -> EVT8aNw4NTvjBL1XR6hgy4zcA9jzh1JLjMuAw85mSbW68vYzw2f9H
        //evtjs -> EVT6Qz3wuRjyN6gaU3P3XRxpnEZnM4oPxortemaWDwFRvsv2FxgND
        try {
            PublicKeysParams publicKeysParams = new PublicKeysParams(new String[]{
                    "EVT8aNw4NTvjBL1XR6hgy4zcA9jzh1JLjMuAw85mSbW68vYzw2f9H"
            });
//            JSONObject res = api.getInfo();
//            System.out.println(res);

//            List<NameableResource> res1 = api.getCreatedDomains(publicKeysParams);
//            res1.stream().forEach(domain -> System.out.println(domain.toString()));

//            List<TokenName> res = api.getOwnedTokens(publicKeysParams);
//            res.stream().forEach(token -> System.out.println(token.toString()));

//            List<NameableResource> res = api.getManagedGroups(publicKeysParams);
//            res.stream().forEach(group -> System.out.println(group.getName()));

//            JSONObject res = api.getCreatedFungibles(publicKeysParams);
//            System.out.println(res);

//            ActionParams actionParams = new ActionParams("testdomainfei1");
//            List<Action> res = api.getActions(actionParams);
//            res.forEach(action -> System.out.println(action.getTrxId()));

//            JSONObject headerState = api.getHeadBlockHeaderState();
//            System.out.println(headerState.getString("id"));
//            JSONArray res = api.getTransactionIdsInBlock(new TextIdParams(headerState.getString("id")));
//            System.out.println(res);

//            TokenDetailParams tokenDetailParams = new TokenDetailParams("nd1545706101478", "tk3091412207.0522");
//            TokenDetailData res1 = api.getToken(tokenDetailParams);
//            System.out.println(res1.getName());
//            res1.getOwner().forEach(publicKey -> System.out.println(publicKey.toString()));

//            NameParams nameParams = new NameParams("testdomainfei1");
//            DomainDetailData res = api.getDomainDetail(nameParams);
//            System.out.println(res.getTransfer());

//            NameParams nameParams = new NameParams("testgroupcreationfei");
//            GroupDetailData res = api.getGroupDetail(nameParams);
//            System.out.println(res.getRoot());

//            FungibleBalanceParams fungibleBalanceParams = new FungibleBalanceParams(
//                    "EVT6Qz3wuRjyN6gaU3P3XRxpnEZnM4oPxortemaWDwFRvsv2FxgND", "1");
//            JSONArray res = api.getFungibleBalance(fungibleBalanceParams);
//            System.out.println(res);

//            TransactionDetailParams transactionDetailParams = new TransactionDetailParams(
//                    "ebee92f7ea4e29020f6ae9cf002d66d6ac4bd1f777a87a8f66746988bc68ac40"
//            );
//
//            TransactionDetail res = api.getTransactionDetailById(transactionDetailParams);
//            System.out.println(res.getTransaction());

            JSONObject res = api.getFungibleSymbolDetail(new IdParams(2));
            System.out.println(res);

        } catch (ApiResponseException ex) {
            System.out.println(String.format("%s (%s)", ex.getMessage(), ex.getRaw()));
        }
    }

    public JSONObject getInfo() throws ApiResponseException {
        return new Info().request(RequestParams.of(netParams));
    }

    public JSONObject getHeadBlockHeaderState() throws ApiResponseException {
        return new HeadBlockHeaderState().request(RequestParams.of(netParams));
    }

    public List<NameableResource> getCreatedDomains(PublicKeysParams publicKeysParams) throws ApiResponseException {
        return new HistoryDomain().request(RequestParams.of(netParams, publicKeysParams));
    }

    public List<TokenName> getOwnedTokens(PublicKeysParams publicKeysParams) throws ApiResponseException {
        return new HistoryToken().request(RequestParams.of(netParams, publicKeysParams));
    }

    public List<NameableResource> getManagedGroups(PublicKeysParams publicKeysParams) throws ApiResponseException {
        return new HistoryGroup().request(RequestParams.of(netParams, publicKeysParams));
    }

    public JSONObject getCreatedFungibles(PublicKeysParams publicKeysParams) throws ApiResponseException {
        return new HistoryFungible().request(RequestParams.of(netParams, publicKeysParams));
    }

    public List<Action> getActions(ActionParams actionParams) throws ApiResponseException {
        return new HistoryAction().request(RequestParams.of(netParams, actionParams));
    }

    public JSONArray getTransactionIdsInBlock(TextIdParams idParams) throws ApiResponseException {
        return new TransactionIds().request(RequestParams.of(netParams, idParams));
    }

    public TokenDetailData getToken(TokenDetailParams tokenDetailParams) throws ApiResponseException {
        return new TokenDetail().request(RequestParams.of(netParams, tokenDetailParams));
    }

    public JSONArray getFungibleBalance(FungibleBalanceParams fungibleBalanceParams) throws ApiResponseException {
        return new FungibleBalance().request(RequestParams.of(netParams, fungibleBalanceParams));
    }

    public TransactionDetail getTransactionDetailById(TransactionDetailParams transactionDetailParams) throws ApiResponseException {
        return new HistoryTransactionDetail().request(RequestParams.of(netParams, transactionDetailParams));
    }

    public DomainDetailData getDomainDetail(NameParams nameParams) throws ApiResponseException {
        return new DomainDetail().request(RequestParams.of(netParams, nameParams));
    }

    public List<Action> getFungibleActionsByAddress(FungibleActionParams fungibleActionParams) throws ApiResponseException {
        return new FungibleAction().request(RequestParams.of(netParams, fungibleActionParams));
    }

    public GroupDetailData getGroupDetail(NameParams nameParams) throws ApiResponseException {
        return new GroupDetail().request(RequestParams.of(netParams, nameParams));
    }

    public JSONObject getFungibleSymbolDetail(IdParams idParams) throws ApiResponseException {
        return new FungibleDetail().request(RequestParams.of(netParams, idParams));
    }
}
