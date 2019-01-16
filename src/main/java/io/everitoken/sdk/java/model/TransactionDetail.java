package io.everitoken.sdk.java.model;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class TransactionDetail {
    private final int blockNum;
    private final String packedTrx;
    private final String id;
    private final String compression;
    private final List<String> signatures;
    private final JSONObject transaction;
    private final String blockId;

    // TODO put signature into signature object
    private TransactionDetail(JSONObject raw) throws JSONException {
        blockNum = raw.getInt("block_num");
        packedTrx = raw.getString("packed_trx");
        id = raw.getString("id");
        compression = raw.getString("compression");
        JSONArray signaturesArray = raw.getJSONArray("signatures");
        signatures = StreamSupport.stream(signaturesArray.spliterator(), false)
                .map(sig -> sig.toString()).collect(Collectors.toList());
        transaction = raw.getJSONObject("transaction");
        blockId = raw.getString("block_id");
    }

    public static TransactionDetail create(JSONObject raw) throws JSONException {
        Objects.requireNonNull(raw);
        return new TransactionDetail(raw);
    }

    public int getBlockNum() {
        return blockNum;
    }

    public String getPackedTrx() {
        return packedTrx;
    }

    public String getId() {
        return id;
    }

    public String getCompression() {
        return compression;
    }

    public List<String> getSignatures() {
        return signatures;
    }

    public JSONObject getTransaction() {
        return transaction;
    }

    public String getBlockId() {
        return blockId;
    }
}