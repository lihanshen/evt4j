package io.everitoken.sdk.java.actions;

public class Action {
    private final ActionNameEnum name;
    private final String abi;

    private Action(ActionNameEnum name, String abi) {
        this.name = name;
        this.abi = abi;
    }

    public static Action createAction() {

    }
}
