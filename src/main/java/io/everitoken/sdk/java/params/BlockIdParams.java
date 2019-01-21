package io.everitoken.sdk.java.params;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.annotation.JSONField;

public class BlockIdParams implements ApiParams {
    private final String id;

    public BlockIdParams(String id) {
        this.id = id;
    }

    @JSONField(name = "block_id")
    public String getId() {
        return id;
    }

    @Override
    public String asBody() {
        return JSON.toJSONString(this);
    }
}
