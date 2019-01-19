package io.everitoken.sdk.java.dto;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

public class NameableResource implements Namable {
    protected String name;

    protected NameableResource(String name) {
        this.name = name;
    }

    @NotNull
    @Contract("_ -> new")
    public static NameableResource create(String name) {
        return new NameableResource(name);
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return String.format("[%s]: %s -> %s", getClass().getSimpleName(), "name", getName());
    }
}
