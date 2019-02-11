package io.everitoken.sdk.java.service;

import com.alibaba.fastjson.JSON;
import io.everitoken.sdk.java.PublicKey;
import io.everitoken.sdk.java.Utils;
import io.everitoken.sdk.java.abi.Abi;
import io.everitoken.sdk.java.abi.AbiSerialisationProviderInterface;
import io.everitoken.sdk.java.abi.RemoteAbiSerialisationProvider;
import io.everitoken.sdk.java.apiResource.Info;
import io.everitoken.sdk.java.apiResource.SigningRequiredKeys;
import io.everitoken.sdk.java.apiResource.TransactionCommit;
import io.everitoken.sdk.java.apiResource.TransactionEstimatedCharge;
import io.everitoken.sdk.java.dto.Charge;
import io.everitoken.sdk.java.dto.NodeInfo;
import io.everitoken.sdk.java.dto.Transaction;
import io.everitoken.sdk.java.dto.TransactionData;
import io.everitoken.sdk.java.exceptions.ApiResponseException;
import io.everitoken.sdk.java.param.NetParams;
import io.everitoken.sdk.java.param.RequestParams;
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

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class TransactionService {
    private final NetParams netParams;
    private final AbiSerialisationProviderInterface actionSerializeProvider;

    private TransactionService(final NetParams netParams, final AbiSerialisationProviderInterface provider) {
        this.netParams = netParams;
        this.actionSerializeProvider = provider;
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

    public TransactionData push(TransactionConfiguration txConfig, List<Abi> actions) throws ApiResponseException {
        final Transaction rawTx = this.buildRawTransaction(txConfig, actions);
        // get signable digest from node
        final byte[] digest = SignProvider.getSignableDigest(this.netParams, rawTx);

        return new TransactionCommit().request(RequestParams.of(this.netParams, () -> {
            // build transaction body
            final JSONObject payload = new JSONObject();
            payload.put("compression", "none");
            payload.put("transaction", new JSONObject(JSON.toJSONString(rawTx)));
            payload.put("signatures", new JSONArray(txConfig.getSignProvider().sign(digest).toString()));
            return payload.toString();
        }));
    }

    public Charge estimateCharge(TransactionConfiguration txConfig, List<Abi> actions, List<PublicKey> availablePublicKeys) throws ApiResponseException {
        final Transaction rawTx = this.buildRawTransaction(txConfig, actions);

        JSONObject txObj = new JSONObject(JSON.toJSONString(rawTx));
        List<String> requiredKeys = new SigningRequiredKeys().request(RequestParams.of(netParams, () -> {
            JSONObject json = new JSONObject();
            json.put("transaction", txObj);
            json.put("available_keys", availablePublicKeys.stream().map(PublicKey::toString).collect(Collectors.toList()));
            return json.toString();
        }));

        return new TransactionEstimatedCharge().request(RequestParams.of(netParams, () -> {
            JSONObject json = new JSONObject();
            json.put("transaction", txObj);
            json.put("sign_num", requiredKeys.size());
            return json.toString();
        }));
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

        // TODO: Dirty hack to sign local time
        final DateTime local = new DateTime();
        final LocalDateTime utc = local.withZone(DateTimeZone.UTC).toLocalDateTime();
        final DateTime newLocal = new DateTime(utc.toString());
        // Dirty hack

        final Duration timeDiffDuration = Duration.millis(dateTime.getMillis() + 70 - newLocal.getMillis());
        final DateTime expiration = dateTime.plus(expireDuration).minus(timeDiffDuration);
        return expiration.toString().substring(0, TIMESTAMP_LENGTH);
    }

    public Transaction buildRawTransaction(final TransactionConfiguration txConfig, final List<Abi> actions) throws ApiResponseException {
        List<String> serializedActions =
                actions.stream()
                        .map(action -> action.serialize(this.actionSerializeProvider))
                        .collect(Collectors.toList());
        final NodeInfo res = (new Info()).request(RequestParams.of(netParams));

        final int refBlockNumber = Utils.getNumHash(res.getLastIrreversibleBlockId());
        final long refBlockPrefix = Utils.getLastIrreversibleBlockPrefix(res.getLastIrreversibleBlockId());
        final String expirationDateTime = TransactionService.getExpirationTime(res.getHeadBlockTime());

        return new Transaction(serializedActions, expirationDateTime, refBlockNumber, refBlockPrefix,
                txConfig.getMaxCharge(),
                txConfig.getPayer()
        );
    }
}
