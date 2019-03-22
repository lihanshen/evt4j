package io.everitoken.sdk.java;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

public class Symbol {
    public static Symbol Evt;

    static {
        Evt = Symbol.of(1, 5);
    }

    private final int precision;
    private final int id;

    private Symbol(int id, int precision) {
        this.precision = precision;
        this.id = id;
    }

    @NotNull
    @Contract("_, _ -> new")
    public static Symbol of(int id, int precision) {
        if (precision >= 18) {
            throw new IllegalArgumentException("Precision of symbol must be less than 18");
        }

        return new Symbol(id, precision);
    }

    public int getPrecision() {
        return precision;
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return String.format("%d,S#%d", precision, id);
    }
}
