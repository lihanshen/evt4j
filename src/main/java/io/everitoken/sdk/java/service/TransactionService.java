package io.everitoken.sdk.java.service;

import com.alibaba.fastjson.JSON;
import io.everitoken.sdk.java.PublicKey;
import io.everitoken.sdk.java.Utils;
import io.everitoken.sdk.java.abi.AbiSerialisationProvider;
import io.everitoken.sdk.java.abi.NewDomainAction;
import io.everitoken.sdk.java.abi.RemoteAbiSerialisationProvider;
import io.everitoken.sdk.java.apiResource.Info;
import io.everitoken.sdk.java.apiResource.SignableDigest;
import io.everitoken.sdk.java.apiResource.TransactionCommit;
import io.everitoken.sdk.java.dto.NodeInfo;
import io.everitoken.sdk.java.dto.Transaction;
import io.everitoken.sdk.java.dto.TransactionData;
import io.everitoken.sdk.java.exceptions.ApiResponseException;
import io.everitoken.sdk.java.param.NetParams;
import io.everitoken.sdk.java.param.RequestParams;
import io.everitoken.sdk.java.param.TestNetNetParams;
import io.everitoken.sdk.java.provider.SignProvider;
import io.everitoken.sdk.java.provider.SingleKeyProvider;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.Duration;
import org.joda.time.LocalDateTime;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

class TransactionConfiguration {
    private final int maxCharge;
    private final PublicKey payer;
    private final boolean estimateCharge;
    private final List<PublicKey> availablePublicKeys;

    public TransactionConfiguration(final int maxCharge, final PublicKey payer, final boolean estimateCharge,
                                    @Nullable final List<PublicKey> availablePublicKeys) {
        this.maxCharge = maxCharge;
        this.payer = payer;
        this.estimateCharge = estimateCharge;
        this.availablePublicKeys = availablePublicKeys;
    }

    public TransactionConfiguration(final int maxCharge, final PublicKey payer) {
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

    public TransactionService(final NetParams netParams, final AbiSerialisationProvider provider) {
        this.netParams = netParams;
        this.provider = provider;
    }

    public static void main(final String[] args) {
        final NetParams netParam = new TestNetNetParams();
        final AbiSerialisationProvider abiSerialisationProvider = new RemoteAbiSerialisationProvider(netParam);
        final String data = "{\"name\":\"test1113\"," +
                "\"creator\":\"EVT6Qz3wuRjyN6gaU3P3XRxpnEZnM4oPxortemaWDwFRvsv2FxgND\",\"issue\":{\"name\":\"issue\"," +
                "\"threshold\":1,\"authorizers\":[{\"ref\":\"[A] " +
                "EVT6Qz3wuRjyN6gaU3P3XRxpnEZnM4oPxortemaWDwFRvsv2FxgND\"," +
                "\"weight\":1}]},\"transfer\":{\"name\":\"transfer\",\"threshold\":1,\"authorizers\":[{\"ref\":\"[G] " +
                ".OWNER\",\"weight\":1}]},\"manage\":{\"name\":\"manage\",\"threshold\":1," +
                "\"authorizers\":[{\"ref\":\"[A]" +
                " EVT6Qz3wuRjyN6gaU3P3XRxpnEZnM4oPxortemaWDwFRvsv2FxgND\",\"weight\":1}]}}";

        final JSONObject json = new JSONObject(data);
        final NewDomainAction newDomainAction = NewDomainAction.ofRaw(
                json.getString("name"),
                json.getString("creator"),
                json.getJSONObject("issue"),
                json.getJSONObject("transfer"),
                json.getJSONObject("manage")
        );
        try {
            final TransactionService transactionService = new TransactionService(netParam, abiSerialisationProvider);
            final TransactionConfiguration txConfig = new TransactionConfiguration(1000000, PublicKey.of(
                    "EVT6Qz3wuRjyN6gaU3P3XRxpnEZnM4oPxortemaWDwFRvsv2FxgND"));
            final Transaction tx = transactionService.buildRawTransaction(
                    txConfig,
                    Arrays.asList(newDomainAction.serialize(abiSerialisationProvider))
            );
            final byte[] digest = TransactionService.getSignableDigestViaApi(netParam, tx);
            final SingleKeyProvider singleKeyProvider = new SingleKeyProvider("5J1by7KRQujRdXrurEsvEr2zQGcdPaMJRjewER6XsAR2eCcpt3D");
            final SignProvider signProvider = SignProvider.of(singleKeyProvider);

            final JSONObject payload = new JSONObject();
            payload.put("compression", "none");
            payload.put("transaction", new JSONObject(JSON.toJSONString(tx)));
            payload.put("signatures", new JSONArray(signProvider.get(digest).toString()));
            System.out.println(payload);
            final TransactionCommit transactionCommit = new TransactionCommit();
            final TransactionData txData = transactionCommit.request(RequestParams.of(netParam, payload::toString));
            System.out.println(txData.getTrxId());
        } catch (final ApiResponseException ex) {
            System.out.println(ex.getRaw());
        }
    }

    @NotNull
    public static String getExpirationTime(final String referenceTime) {
        return getExpirationTime(referenceTime, null);
    }

    @NotNull
    public static String getExpirationTime(@NotNull final String referenceTime, @Nullable final String type) {
        Objects.requireNonNull(referenceTime);

        final int TIMESTAMP_LENGTH = 19;
        Duration expireDuration = Duration.standardSeconds(1000);

        if (type != null && type.equals("everipay")) {
            expireDuration = Duration.standardSeconds(10);
        }

        final DateTime dateTime = new DateTime(referenceTime);

        // TODO: Dirty hack to get local time
        final DateTime local = new DateTime();
        final LocalDateTime utc = local.withZone(DateTimeZone.UTC).toLocalDateTime();
        final DateTime newLocal = new DateTime(utc.toString());
        // Dirty hack

        final Duration timeDiffDuration = Duration.millis(dateTime.getMillis() + 70 - newLocal.getMillis());
        final DateTime expiration = dateTime.plus(expireDuration).minus(timeDiffDuration);
        return expiration.toString().substring(0, TIMESTAMP_LENGTH);
    }

    public static byte[] getSignableDigestViaApi(final NetParams netParams, final Transaction tx) throws ApiResponseException {
        final SignableDigest signableDigest = new SignableDigest();
        return signableDigest.request(RequestParams.of(netParams, () -> JSON.toJSONString(tx)));
    }

    public Transaction buildRawTransaction(final TransactionConfiguration txConfig, final List<String> actions) throws ApiResponseException {
        final Info info = new Info();
        final NodeInfo res = info.request(RequestParams.of(netParams));

        final int refBlockNumber = Utils.getNumHash(res.getLastIrreversibleBlockId());
        final long refBlockPrefix = Utils.getLastIrreversibleBlockPrefix(res.getLastIrreversibleBlockId());
        final String expirationDateTime = TransactionService.getExpirationTime(res.getHeadBlockTime());

        return new Transaction(actions, expirationDateTime, refBlockNumber, refBlockPrefix,
                txConfig.getMaxCharge(),
                txConfig.getPayer()
        );
    }
}
