package io.everitoken.sdk.java.model;

public abstract class NameableResource {
    private String name;

    public NameableResource(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public String toString() {
        return String.format("[%s]: %s -> %s", getClass().getSimpleName(), "name", getName());
    }
}
