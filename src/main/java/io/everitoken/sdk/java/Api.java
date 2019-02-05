package io.everitoken.sdk.java;

import io.everitoken.sdk.java.apiResources.*;
import io.everitoken.sdk.java.dto.*;
import io.everitoken.sdk.java.exceptions.ApiResponseException;
import io.everitoken.sdk.java.provider.KeyProvider;
import io.everitoken.sdk.java.params.*;
import org.json.JSONArray;
import org.json.JSONObject;

import javax.annotation.Nullable;
import java.util.List;

public class Api {
    private final NetParams netParams;
    private final KeyProvider keyProvider;

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
                    "EVT6Qz3wuRjyN6gaU3P3XRxpnEZnM4oPxortemaWDwFRvsv2FxgND"
            });
//            JSONObject res = api.getHeadBlockHeaderState();
//            System.out.println(res);

//            List<NameableResource> res1 = api.getCreatedDomains(publicKeysParams);
//            System.out.println(JSON.toJSONString(res1));

//            List<TokenDomain> res = api.getOwnedTokens(publicKeysParams);
//            System.out.println(JSON.toJSONString(res));

//            List<NameableResource> res = api.getManagedGroups(publicKeysParams);
//            System.out.println(JSON.toJSONString(res));

//            FungibleCreated res = api.getCreatedFungibles(publicKeysParams);
//            System.out.println(res);

//            ActionQueryParams actionParams = new ActionQueryParams("testdomainfei1", null, new
//            String[]{"issuetoken", "transfer"
//            });
//            List<ActionData> res = api.getActions(actionParams);
//            System.out.println(JSON.toJSONString(res));

//            JSONObject headerState = api.getHeadBlockHeaderState();
//            System.out.println(headerState.getString("id"));
//            JSONArray res = api.getTransactionIdsInBlock(new BlockIdParams(headerState.getString("id")));
//            System.out.println(res);

//            TokenDetailParams tokenDetailParams = new TokenDetailParams("nd1545706101478", "tk3091412207.0522");
//            TokenDetailData res1 = api.getToken(tokenDetailParams);
//            System.out.println(res1.getName());
//            res1.getOwner().forEach(publicKey -> System.out.println(publicKey.toString()));

//            NameParams nameParams = new NameParams("testdomainfei1");
//            DomainDetailData res = api.getDomainDetail(nameParams);
//            System.out.println(res.getTransfer().getAuthorizers().get(0).getRef());

//            NameParams nameParams = new NameParams("testgroupcreationfei");
//            GroupDetailData res = api.getGroupDetail(nameParams);
//            System.out.println(res.getRoot());

//            FungibleBalanceParams fungibleBalanceParams = new FungibleBalanceParams(
//                    "EVT6Qz3wuRjyN6gaU3P3XRxpnEZnM4oPxortemaWDwFRvsv2FxgND", "1");
//            List<FungibleBalanceData> res = api.getFungibleBalance(fungibleBalanceParams);
//            res.forEach(balance -> System.out.println(balance.getRaw()));
//            System.out.println(JSON.toJSONString(res));

//            ActionQueryParams actionParams = new ActionQueryParams("testdomainfei1");
//            List<ActionData> actionData = api.getActions(actionParams);
//            System.out.println(Utils.jsonPrettyPrint(actionData));

//            TransactionDetailParams transactionDetailParams = new TransactionDetailParams(
//                    "ebee92f7ea4e29020f6ae9cf002d66d6ac4bd1f777a87a8f66746988bc68ac40"
//            );
//            TransactionDetail res = api.getTransactionDetailById(transactionDetailParams);
//            System.out.println(res.getTransaction());
//            FungibleDetailData res = api.getFungibleSymbolDetail(new IdParams(1));
//            System.out.println(Utils.jsonPrettyPrint(res));

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    public NodeInfo getInfo() throws ApiResponseException {
        return new Info().request(RequestParams.of(netParams));
    }

    public JSONObject getHeadBlockHeaderState() throws ApiResponseException {
        return new HeadBlockHeaderState().request(RequestParams.of(netParams));
    }

    public List<NameableResource> getCreatedDomains(PublicKeysParams publicKeysParams) throws ApiResponseException {
        return new HistoryDomain().request(RequestParams.of(netParams, publicKeysParams));
    }

    public List<TokenDomain> getOwnedTokens(PublicKeysParams publicKeysParams) throws ApiResponseException {
        return new HistoryToken().request(RequestParams.of(netParams, publicKeysParams));
    }

    public List<NameableResource> getManagedGroups(PublicKeysParams publicKeysParams) throws ApiResponseException {
        return new HistoryGroup().request(RequestParams.of(netParams, publicKeysParams));
    }

    public FungibleCreated getCreatedFungibles(PublicKeysParams publicKeysParams) throws ApiResponseException {
        return new HistoryFungible().request(RequestParams.of(netParams, publicKeysParams));
    }

    public List<ActionData> getActions(ActionQueryParams actionQueryParams) throws ApiResponseException {
        return new HistoryAction().request(RequestParams.of(netParams, actionQueryParams));
    }

    public JSONArray getTransactionIdsInBlock(BlockIdParams idParams) throws ApiResponseException {
        return new TransactionIds().request(RequestParams.of(netParams, idParams));
    }

    public TokenDetailData getToken(TokenDetailParams tokenDetailParams) throws ApiResponseException {
        return new TokenDetail().request(RequestParams.of(netParams, tokenDetailParams));
    }

    public List<FungibleBalanceData> getFungibleBalance(FungibleBalanceParams fungibleBalanceParams) throws ApiResponseException {
        return new FungibleBalance().request(RequestParams.of(netParams, fungibleBalanceParams));
    }

    public TransactionDetail getTransactionDetailById(TransactionDetailParams transactionDetailParams) throws ApiResponseException {
        return new HistoryTransactionDetail().request(RequestParams.of(netParams, transactionDetailParams));
    }

    public DomainDetailData getDomainDetail(NameParams nameParams) throws ApiResponseException {
        return new DomainDetail().request(RequestParams.of(netParams, nameParams));
    }

    public List<ActionData> getFungibleActionsByAddress(FungibleActionParams fungibleActionParams) throws ApiResponseException {
        return new FungibleAction().request(RequestParams.of(netParams, fungibleActionParams));
    }

    public GroupDetailData getGroupDetail(NameParams nameParams) throws ApiResponseException {
        return new GroupDetail().request(RequestParams.of(netParams, nameParams));
    }

    public FungibleDetailData getFungibleSymbolDetail(IdParams idParams) throws ApiResponseException {
        return new FungibleDetail().request(RequestParams.of(netParams, idParams));
    }
}
