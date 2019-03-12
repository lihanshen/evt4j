package io.everitoken.sdk.java.param;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.annotation.JSONField;

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

    @JSONField(name = "id")
    public String getTrxId() {
        return trxId;
    }

    @JSONField(name = "block_num")
    public String getBlockNum() {
        return blockNum;
    }

    @Override
    public String asBody() {
        return JSON.toJSONString(this);
    }
}
