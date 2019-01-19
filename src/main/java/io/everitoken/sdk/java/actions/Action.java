package io.everitoken.sdk.java.actions;

public class Action {
    private final ActionNameEnum name;
    private final Abi abi;
    private final String domain;
    private final String key;

    private Action(ActionNameEnum name, Abi abi, String domain, String key) {
        this.name = name;
        this.abi = abi;
        this.domain = domain;
        this.key = key;
    }
}
