package io.everitoken.sdk.java.example;

import com.alibaba.fastjson.JSON;
import io.everitoken.sdk.java.Address;
import io.everitoken.sdk.java.Api;
import io.everitoken.sdk.java.Asset;
import io.everitoken.sdk.java.dto.*;
import io.everitoken.sdk.java.exceptions.ApiResponseException;
import io.everitoken.sdk.java.param.ActionQueryParams;
import io.everitoken.sdk.java.param.PublicKeysParams;
import io.everitoken.sdk.java.param.TestNetNetParams;
import io.everitoken.sdk.java.param.TransactionDetailParams;

import java.util.List;

public class ApiExample {
    public static void main(String[] args) {
        try {
            // replace this with method you want to test
            getToken();
        } catch (ApiResponseException ex) {

        }
    }

    static void testDomainTokens() throws ApiResponseException {
        TestNetNetParams netParams = new TestNetNetParams();
        List<TokenDetailData> testdomainfei1 = new Api(netParams).getDomainTokens("testdomainfei1", 10, 0);
        testdomainfei1.stream().forEach(tokenDetailData -> {
            System.out.println(tokenDetailData.getName());
        });
    }

    static void getSuspendedProposalByName() throws ApiResponseException {
        TestNetNetParams netParams = new TestNetNetParams();
        String suspendedProposal = new Api(netParams).getSuspendedProposal("testProposal10");
        System.out.println(suspendedProposal);
    }

    static void getCreatedDomain() throws ApiResponseException {
        TestNetNetParams netParams = new TestNetNetParams();
        final PublicKeysParams publicKeysParams = new PublicKeysParams(new String[]{
                "EVT6Qz3wuRjyN6gaU3P3XRxpnEZnM4oPxortemaWDwFRvsv2FxgND"
        });

        List<NameableResource> res = new Api(netParams).getCreatedDomains(publicKeysParams);
        System.out.println(JSON.toJSONString(res));
    }

    static void getOwnedTokens() throws ApiResponseException {
        TestNetNetParams netParams = new TestNetNetParams();
        final PublicKeysParams publicKeysParams = new PublicKeysParams(new String[]{
                "EVT6Qz3wuRjyN6gaU3P3XRxpnEZnM4oPxortemaWDwFRvsv2FxgND"
        });

        List<TokenDomain> res = new Api(netParams).getOwnedTokens(publicKeysParams);
        System.out.println(JSON.toJSONString(res));
    }

    static void getManagedGroups() throws ApiResponseException {
        TestNetNetParams netParams = new TestNetNetParams();
        final PublicKeysParams publicKeysParams = new PublicKeysParams(new String[]{
                "EVT6Qz3wuRjyN6gaU3P3XRxpnEZnM4oPxortemaWDwFRvsv2FxgND"
        });

        List<NameableResource> res = new Api(netParams).getManagedGroups(publicKeysParams);
        System.out.println(JSON.toJSONString(res));
    }

    static void getCreatedFungibles() throws ApiResponseException {
        TestNetNetParams netParams = new TestNetNetParams();
        final PublicKeysParams publicKeysParams = new PublicKeysParams(new String[]{
                "EVT6Qz3wuRjyN6gaU3P3XRxpnEZnM4oPxortemaWDwFRvsv2FxgND"
        });

        FungibleCreated res = new Api(netParams).getCreatedFungibles(publicKeysParams);
        System.out.println(res);
    }

    static void getActions() throws ApiResponseException {
        TestNetNetParams netParams = new TestNetNetParams();
        ActionQueryParams actionParams = new ActionQueryParams("testdomainfei1", null, new
                String[]{"issuetoken", "transfer"
        });
        List<ActionData> res = new Api(netParams).getActions(actionParams);
        System.out.println(JSON.toJSONString(res));
    }

    static void getToken() throws ApiResponseException {
        TestNetNetParams netParams = new TestNetNetParams();
        TokenDetailData res = new Api(netParams).getToken("nd1545706101478", "tk3091412207.0522");
        System.out.println(res.getName());
        res.getOwner().forEach(publicKey -> System.out.println(publicKey.toString()));
    }

    static void getDomainDetail() throws ApiResponseException {
        TestNetNetParams netParams = new TestNetNetParams();
        DomainDetailData res = new Api(netParams).getDomainDetail("testdomainfei1");
        System.out.println(res.getTransfer().getAuthorizers().get(0).getRef());
    }

    static void getGroupDetail() throws ApiResponseException {
        TestNetNetParams netParams = new TestNetNetParams();
        GroupDetailData res = new Api(netParams).getGroupDetail("testgroupcreationfei");
        System.out.println(res.getRoot());
    }

    static void getFungibleBalance() throws ApiResponseException {
        TestNetNetParams netParams = new TestNetNetParams();
        List<Asset> res = new Api(netParams).getFungibleBalance(Address.of(
                "EVT8aNw4NTvjBL1XR6hgy4zcA9jzh1JLjMuAw85mSbW68vYzw2f9H"));
        res.forEach(balance -> System.out.println(balance.toString()));
    }

    static void getTransactionDetailById() throws ApiResponseException {
        TestNetNetParams netParams = new TestNetNetParams();
        TransactionDetailParams transactionDetailParams = new TransactionDetailParams(
                "d7032971c96637ae3f541a16b3c7cfd4fef4859082eb7ecc925644854bda5c6b"
        );
        TransactionDetail res = new Api(netParams).getTransactionDetailById(transactionDetailParams);
        System.out.println(res.getTransaction());
    }

    static void getFungibleSymbolDetail() throws ApiResponseException {
        TestNetNetParams netParams = new TestNetNetParams();
        FungibleDetailData res = new Api(netParams).getFungibleSymbolDetail(1);
        System.out.println(res.getCreator());
    }
}
