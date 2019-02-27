package io.everitoken.sdk.java;

import io.everitoken.sdk.java.apiResource.*;
import io.everitoken.sdk.java.dto.*;
import io.everitoken.sdk.java.exceptions.ApiResponseException;
import io.everitoken.sdk.java.param.*;
import io.everitoken.sdk.java.provider.KeyProvider;
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

    public Api(NetParams netParams) {
        this(netParams, null);
    }

    public Api() {
        this(new TestNetNetParams(), null);
    }

    public static void main(String[] args) {
        Api api = new Api();
        // evtjava -> EVT8aNw4NTvjBL1XR6hgy4zcA9jzh1JLjMuAw85mSbW68vYzw2f9H
        //evtjs -> EVT6Qz3wuRjyN6gaU3P3XRxpnEZnM4oPxortemaWDwFRvsv2FxgND
        try {
//            final PublicKeysParams publicKeysParams = new PublicKeysParams(new String[]{
//                    "EVT6Qz3wuRjyN6gaU3P3XRxpnEZnM4oPxortemaWDwFRvsv2FxgND"
//            });
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
//            JSONArray res = api.getTransactionIdsInBlock(headerState.getString("id"));
//            System.out.println(res);

//            TokenDetailData res1 = api.getToken("nd1545706101478", "tk3091412207.0522");
//            System.out.println(res1.getName());
//            res1.getOwner().forEach(publicKey -> System.out.println(publicKey.toString()));

//            DomainDetailData res = api.getDomainDetail("testdomainfei1");
//            System.out.println(res.getTransfer().getAuthorizers().sign(0).getRef());

//            GroupDetailData res = api.getGroupDetail("testgroupcreationfei");
//            System.out.println(res.getRoot());

//            List<Asset> res = api.getFungibleBalance(Address.of(
//                    "EVT8aNw4NTvjBL1XR6hgy4zcA9jzh1JLjMuAw85mSbW68vYzw2f9H"));
//            res.forEach(balance -> System.out.println(balance.toString()));

//            ActionQueryParams actionParams = new ActionQueryParams("testdomainfei1");
//            List<ActionData> actionData = api.getActions(actionParams);
//            System.out.println(Utils.jsonPrettyPrint(actionData));

//            TransactionDetailParams transactionDetailParams = new TransactionDetailParams(
//                    "d7032971c96637ae3f541a16b3c7cfd4fef4859082eb7ecc925644854bda5c6b"
//            );
//            TransactionDetail res = api.getTransactionDetailById(transactionDetailParams);
//            System.out.println(res.getTransaction());
//            FungibleDetailData res = api.getFungibleSymbolDetail(20);
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

    public JSONArray getTransactionIdsInBlock(String blockId) throws ApiResponseException {
        return new TransactionIds().request(RequestParams.of(netParams, () -> {
            JSONObject body = new JSONObject();
            body.put("block_id", blockId);
            return body.toString();
        }));
    }

    public TokenDetailData getToken(String domain, String name) throws ApiResponseException {
        return new TokenDetail().request(RequestParams.of(netParams, () -> {
            JSONObject body = new JSONObject();
            body.put("domain", domain);
            body.put("name", name);
            return body.toString();
        }));
    }

    public List<Asset> getFungibleBalance(Address address) throws ApiResponseException {
        return new FungibleBalance().request(RequestParams.of(netParams, () -> {
            JSONObject body = new JSONObject();
            body.put("addr", address.getAddress());
            return body.toString();
        }));
    }

    public TransactionDetail getTransactionDetailById(TransactionDetailParams transactionDetailParams) throws ApiResponseException {
        return new HistoryTransactionDetail().request(RequestParams.of(netParams, transactionDetailParams));
    }

    public DomainDetailData getDomainDetail(String name) throws ApiResponseException {
        return new DomainDetail().request(RequestParams.of(netParams, () -> {
            JSONObject body = new JSONObject();
            body.put("name", name);
            return body.toString();
        }));
    }

    public List<ActionData> getFungibleActionsByAddress(FungibleActionParams fungibleActionParams) throws ApiResponseException {
        return new FungibleAction().request(RequestParams.of(netParams, fungibleActionParams));
    }

    public GroupDetailData getGroupDetail(String name) throws ApiResponseException {
        return new GroupDetail().request(RequestParams.of(netParams, () -> {
            JSONObject body = new JSONObject();
            body.put("name", name);
            return body.toString();
        }));
    }

    public FungibleDetailData getFungibleSymbolDetail(int id) throws ApiResponseException {
        return new FungibleDetail().request(RequestParams.of(netParams, () -> {
            JSONObject body = new JSONObject();
            body.put("id", id);
            return body.toString();
        }));
    }
}
