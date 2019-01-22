package io.everitoken.sdk.java.dto;

import com.alibaba.fastjson.annotation.JSONField;

public class NodeInfo {
    private final String headBlockProducer;
    private final String evtApiVersion;
    private final int headBlockNum;
    private final String chainId;
    private final String lastIrreversibleBlockId;
    private final String headBlockTime;
    private final int lastIrreversibleBlockNum;
    private final String serverVersion;
    private final String headBlockId;
    private final String serverVersionString;

    public NodeInfo(String headBlockProducer,
                    String evtApiVersion,
                    int headBlockNum,
                    String chainId,
                    String lastIrreversibleBlockId,
                    String headBlockTime,
                    int lastIrreversibleBlockNum,
                    String serverVersion,
                    String headBlockId,
                    String serverVersionString
    ) {
        this.headBlockProducer = headBlockProducer;
        this.evtApiVersion = evtApiVersion;
        this.headBlockNum = headBlockNum;
        this.chainId = chainId;
        this.lastIrreversibleBlockId = lastIrreversibleBlockId;
        this.headBlockTime = headBlockTime;
        this.lastIrreversibleBlockNum = lastIrreversibleBlockNum;
        this.serverVersion = serverVersion;
        this.headBlockId = headBlockId;
        this.serverVersionString = serverVersionString;
    }

    @JSONField(name = "head_block_producer")
    public String getHeadBlockProducer() {
        return headBlockProducer;
    }

    @JSONField(name = "evt_api_version")
    public String getEvtApiVersion() {
        return evtApiVersion;
    }

    @JSONField(name = "head_block_num")
    public int getHeadBlockNum() {
        return headBlockNum;
    }

    @JSONField(name = "chain_id")
    public String getChainId() {
        return chainId;
    }

    @JSONField(name = "last_irreversible_block_id")
    public String getLastIrreversibleBlockId() {
        return lastIrreversibleBlockId;
    }

    @JSONField(name = "head_block_time")
    public String getHeadBlockTime() {
        return headBlockTime;
    }

    @JSONField(name = "last_irreversible_block_num")
    public int getLastIrreversibleBlockNum() {
        return lastIrreversibleBlockNum;
    }

    @JSONField(name = "server_version")
    public String getServerVersion() {
        return serverVersion;
    }

    @JSONField(name = "head_block_id")
    public String getHeadBlockId() {
        return headBlockId;
    }

    @JSONField(name = "server_version_string")
    public String getServerVersionString() {
        return serverVersionString;
    }
}
