package io.everitoken.sdk.java.actions;

public enum ActionNameEnum {
    UPDATEDOMAIN("updatedomain"),
    NEWDOMAIN("newdomain");

    private final String name;

    ActionNameEnum(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
