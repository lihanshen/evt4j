package io.everitoken.sdk.java.service;

import com.alibaba.fastjson.JSON;
import io.everitoken.sdk.java.PublicKey;
import io.everitoken.sdk.java.Utils;
import io.everitoken.sdk.java.abi.AbiSerialisationProvider;
import io.everitoken.sdk.java.abi.NewDomainAction;
import io.everitoken.sdk.java.abi.RemoteAbiSerialisationProvider;
import io.everitoken.sdk.java.apiResources.Info;
import io.everitoken.sdk.java.apiResources.SignableDigest;
import io.everitoken.sdk.java.dto.NodeInfo;
import io.everitoken.sdk.java.dto.Transaction;
import io.everitoken.sdk.java.exceptions.ApiResponseException;
import io.everitoken.sdk.java.params.NetParams;
import io.everitoken.sdk.java.params.RequestParams;
import io.everitoken.sdk.java.params.TestNetNetParams;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.Duration;
import org.joda.time.LocalDateTime;
import org.json.JSONObject;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

class TransactionConfiguration {
    private final int maxCharge;
    private final PublicKey payer;
    private final boolean estimateCharge;
    private final List<PublicKey> availablePublicKeys;

    public TransactionConfiguration(int maxCharge, PublicKey payer, boolean estimateCharge,
                                    @Nullable List<PublicKey> availablePublicKeys) {
        this.maxCharge = maxCharge;
        this.payer = payer;
        this.estimateCharge = estimateCharge;
        this.availablePublicKeys = availablePublicKeys;
    }

    public TransactionConfiguration(int maxCharge, PublicKey payer) {
        this(maxCharge, payer, false, null);
    }

    public int getMaxCharge() {
        return maxCharge;
    }

    public String getPayer() {
        return payer.toString();
    }

    public boolean isEstimateCharge() {
        return estimateCharge;
    }

    public List<PublicKey> getAvailablePublicKeys() {
        return availablePublicKeys;
    }
}

public class TransactionService {
    private final NetParams netParams;
    private final AbiSerialisationProvider provider;

    public TransactionService(NetParams netParams, AbiSerialisationProvider provider) {
        this.netParams = netParams;
        this.provider = provider;
    }

    public static void main(String[] args) {
        NetParams netParam = new TestNetNetParams();
        AbiSerialisationProvider abiSerialisationProvider = new RemoteAbiSerialisationProvider(netParam);
        String data = "{\"name\":\"test111\"," +
                "\"creator\":\"EVT6Qz3wuRjyN6gaU3P3XRxpnEZnM4oPxortemaWDwFRvsv2FxgND\",\"issue\":{\"name\":\"issue\"," +
                "\"threshold\":1,\"authorizers\":[{\"ref\":\"[A] " +
                "EVT6Qz3wuRjyN6gaU3P3XRxpnEZnM4oPxortemaWDwFRvsv2FxgND\"," +
                "\"weight\":1}]},\"transfer\":{\"name\":\"transfer\",\"threshold\":1,\"authorizers\":[{\"ref\":\"[G] " +
                ".OWNER\",\"weight\":1}]},\"manage\":{\"name\":\"manage\",\"threshold\":1," +
                "\"authorizers\":[{\"ref\":\"[A]" +
                " EVT6Qz3wuRjyN6gaU3P3XRxpnEZnM4oPxortemaWDwFRvsv2FxgND\",\"weight\":1}]}}";

        JSONObject json = new JSONObject(data);
        NewDomainAction newDomainAction = NewDomainAction.ofRaw(
                json.getString("name"),
                json.getString("creator"),
                json.getJSONObject("issue"),
                json.getJSONObject("transfer"),
                json.getJSONObject("manage")
        );
        try {
            TransactionService transactionService = new TransactionService(netParam, abiSerialisationProvider);
            TransactionConfiguration txConfig = new TransactionConfiguration(1000000, PublicKey.of(
                    "EVT6Qz3wuRjyN6gaU3P3XRxpnEZnM4oPxortemaWDwFRvsv2FxgND"));
            Transaction tx = transactionService.buildRawTransaction(
                    txConfig,
                    Arrays.asList(newDomainAction.serialize(abiSerialisationProvider))
            );
            byte[] digest = TransactionService.getSignableDigestViaApi(netParam, tx);
            System.out.println(Utils.HEX.encode(digest));
        } catch (ApiResponseException ex) {
            System.out.println(ex.getRaw());
        }
    }

    @NotNull
    public static String getExpirationTime(String referenceTime) {
        return getExpirationTime(referenceTime, null);
    }

    @NotNull
    public static String getExpirationTime(@NotNull String referenceTime, @Nullable String type) {
        Objects.requireNonNull(referenceTime);

        int TIMESTAMP_LENGTH = 19;
        Duration expireDuration = Duration.standardSeconds(1000);

        if (type != null && type.equals("everipay")) {
            expireDuration = Duration.standardSeconds(10);
        }

        DateTime dateTime = new DateTime(referenceTime);

        // TODO: Dirty hack to get local time
        DateTime local = new DateTime();
        LocalDateTime utc = local.withZone(DateTimeZone.UTC).toLocalDateTime();
        DateTime newLocal = new DateTime(utc.toString());
        // Dirty hack

        Duration timeDiffDuration = Duration.millis(dateTime.getMillis() + 70 - newLocal.getMillis());
        DateTime expiration = dateTime.plus(expireDuration).minus(timeDiffDuration);
        return expiration.toString().substring(0, TIMESTAMP_LENGTH);
    }

    public static byte[] getSignableDigestViaApi(NetParams netParams, Transaction tx) throws ApiResponseException {
        SignableDigest signableDigest = new SignableDigest();
        return signableDigest.request(RequestParams.of(netParams, () -> JSON.toJSONString(tx)));
    }

    public Transaction buildRawTransaction(TransactionConfiguration txConfig, List<String> actions) throws ApiResponseException {
        Info info = new Info();
        NodeInfo res = info.request(RequestParams.of(netParams));

        short refBlockNumber = Utils.getNumHash(res.getLastIrreversibleBlockId());
        long refBlockPrefix = Utils.getLastIrreversibleBlockPrefix(res.getLastIrreversibleBlockId());
        String expirationDateTime = TransactionService.getExpirationTime(res.getHeadBlockTime());

        return new Transaction(actions, expirationDateTime, refBlockNumber, refBlockPrefix,
                               txConfig.getMaxCharge(),
                               txConfig.getPayer()
        );
    }
}
