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
