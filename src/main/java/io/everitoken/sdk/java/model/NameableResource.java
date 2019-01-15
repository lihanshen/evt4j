package io.everitoken.sdk.java.model;

public class NameableResource implements Namable {
    protected String name;

    protected NameableResource(String name) {
        this.name = name;
    }

    public static NameableResource create(String name) {
        return new NameableResource(name);
    }

    public String getName() {
        return name;
    }

    public String toString() {
        return String.format("[%s]: %s -> %s", getClass().getSimpleName(), "name", getName());
    }
}
