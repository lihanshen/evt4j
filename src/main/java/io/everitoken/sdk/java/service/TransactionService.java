package io.everitoken.sdk.java.service;

import com.alibaba.fastjson.JSON;
import io.everitoken.sdk.java.PublicKey;
import io.everitoken.sdk.java.Utils;
import io.everitoken.sdk.java.abi.Abi;
import io.everitoken.sdk.java.abi.AbiSerialisationProviderInterface;
import io.everitoken.sdk.java.abi.NewDomainAction;
import io.everitoken.sdk.java.abi.RemoteAbiSerialisationProvider;
import io.everitoken.sdk.java.apiResource.Info;
import io.everitoken.sdk.java.apiResource.TransactionCommit;
import io.everitoken.sdk.java.dto.NodeInfo;
import io.everitoken.sdk.java.dto.Transaction;
import io.everitoken.sdk.java.dto.TransactionData;
import io.everitoken.sdk.java.exceptions.ApiResponseException;
import io.everitoken.sdk.java.param.NetParams;
import io.everitoken.sdk.java.param.RequestParams;
import io.everitoken.sdk.java.param.TestNetNetParams;
import io.everitoken.sdk.java.provider.KeyProvider;
import io.everitoken.sdk.java.provider.SignProvider;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.Duration;
import org.joda.time.LocalDateTime;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

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
    private final AbiSerialisationProviderInterface provider;

    private TransactionService(final NetParams netParams, final AbiSerialisationProviderInterface provider) {
        this.netParams = netParams;
        this.provider = provider;
    }

    @NotNull
    @Contract("_ -> new")
    public static TransactionService of(final NetParams netParams) {
        return new TransactionService(netParams, new RemoteAbiSerialisationProvider(netParams));
    }

    @NotNull
    @Contract("_, _ -> new")
    public static TransactionService of(final NetParams netParams, final AbiSerialisationProviderInterface provider) {
        return new TransactionService(netParams, provider);
    }

    public static void main(final String[] args) {
        final NetParams netParam = new TestNetNetParams();
        final String data = "{\"name\":\"test1119\"," +
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
            TransactionService transactionService = TransactionService.of(netParam);
            final TransactionConfiguration txConfig = new TransactionConfiguration(1000000, PublicKey.of(
                    "EVT6Qz3wuRjyN6gaU3P3XRxpnEZnM4oPxortemaWDwFRvsv2FxgND"));
            TransactionData txData = transactionService.push(txConfig, Collections.singletonList(newDomainAction));
            System.out.println(txData.getTrxId());
        } catch (final ApiResponseException ex) {
            System.out.println(ex.getRaw());
        }
    }

    public TransactionData push(TransactionConfiguration txConfig, List<Abi> actions) throws ApiResponseException {
        List<String> serializedActions =
                actions.stream().map(action -> action.serialize(this.provider)).collect(Collectors.toList());
        final Transaction rawTx = this.buildRawTransaction(
                txConfig,
                serializedActions
        );

        final byte[] digest = SignProvider.getSignableDigest(this.netParams, rawTx);
        final KeyProvider keyProvider = KeyProvider.of("5J1by7KRQujRdXrurEsvEr2zQGcdPaMJRjewER6XsAR2eCcpt3D");
        final SignProvider signProvider = SignProvider.of(keyProvider);

        final JSONObject payload = new JSONObject();
        payload.put("compression", "none");
        payload.put("transaction", new JSONObject(JSON.toJSONString(rawTx)));
        payload.put("signatures", new JSONArray(signProvider.get(digest).toString()));
        System.out.println(payload);
        final TransactionData txData = new TransactionCommit().request(RequestParams.of(this.netParams, payload::toString));
        return txData;
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

    public Transaction buildRawTransaction(final TransactionConfiguration txConfig, final List<String> actions) throws ApiResponseException {
        final NodeInfo res = (new Info()).request(RequestParams.of(netParams));

        final int refBlockNumber = Utils.getNumHash(res.getLastIrreversibleBlockId());
        final long refBlockPrefix = Utils.getLastIrreversibleBlockPrefix(res.getLastIrreversibleBlockId());
        final String expirationDateTime = TransactionService.getExpirationTime(res.getHeadBlockTime());

        return new Transaction(actions, expirationDateTime, refBlockNumber, refBlockPrefix,
                txConfig.getMaxCharge(),
                txConfig.getPayer()
        );
    }
}
