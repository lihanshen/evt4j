package io.everitoken.sdk.java.params;

import org.json.JSONObject;

import javax.annotation.Nullable;

public class TransactionDetailParams implements ApiParams {
    private final String trxId;
    private final String blockNum;

    public TransactionDetailParams(String trxId, @Nullable String blockNum) {
        this.trxId = trxId;
        this.blockNum = blockNum;
    }

    public TransactionDetailParams(String trxId) {
        this(trxId, null);
    }

    @Override
    public JSONObject asJson() {
        JSONObject jsonObject = new JSONObject();

        jsonObject.put("id", trxId);

        if (blockNum != null) {
            jsonObject.put("block_num", blockNum);
        }

        return jsonObject;
    }
}
