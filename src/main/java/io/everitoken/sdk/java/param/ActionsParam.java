package io.everitoken.sdk.java.param;

import com.alibaba.fastjson.JSON;
import io.everitoken.sdk.java.abi.Action;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

// TODO write test
public class ActionsParam implements ApiParams {
    private final List<Action> actions;

    private ActionsParam(List<Action> actions) {
        this.actions = actions;
    }

    public static ActionsParam of(Action action) {
        Objects.requireNonNull(action);
        return new ActionsParam(Collections.singletonList(action));
    }

    public static ActionsParam of(List<Action> actions) {
        Objects.requireNonNull(actions);
        return new ActionsParam(actions);
    }

    @Override
    public String asBody() {
        return JSON.toJSONString(this);
    }
}
