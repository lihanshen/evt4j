package io.everitoken.sdk.java.abi;

import com.alibaba.fastjson.annotation.JSONField;
import org.jetbrains.annotations.NotNull;

class AbiToBin<T> {
    private final String action;
    private final T args;

    AbiToBin(@NotNull String action, @NotNull T args) {
        this.action = action;
        this.args = args;
    }

    public String getAction() {
        return action;
    }

    @JSONField(ordinal = 1)
    public T getArgs() {
        return args;
    }
}

