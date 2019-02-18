package io.everitoken.sdk.java;

import io.everitoken.sdk.java.exceptions.InvalidFungibleBalanceException;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.spongycastle.util.Strings;

public class Asset {
    private final int balance;
    private final Symbol symbol;

    private Asset(Symbol symbol, int balance) {
        this.symbol = symbol;
        this.balance = balance;
    }

    @NotNull
    @Contract("_, _ -> new")
    public static Asset of(Symbol symbol, int balance) {
        return new Asset(symbol, balance);
    }

    @NotNull
    @Contract("_ -> new")
    public static Asset parseFromRawBalance(String balance) {
        return parseFromRawBalance(balance, null);
    }

    @NotNull
    public static Asset parseFromRawBalance(String balance, @Nullable Symbol symbol) {
        String[] parts = Strings.split(balance, ' ');
        int id;
        int precision;

        if (parts.length != 2) {
            throw new IllegalArgumentException(
                    String.format("Failed to parse balance: \"1.00000 S#1\" is expected, %s is passed in.", balance)
            );
        }

        String[] balanceParts = Strings.split(parts[0], '.');

        if (balanceParts.length != 2) {
            throw new IllegalArgumentException(String.format(
                    "Failed to parse precision in balance. A \".\" is " +
                            "expected, \"%s\" is passed in",
                    balance
            ));
        }

        precision = balanceParts[1].length();

        try {
            String[] symbolArray = Strings.split(parts[1], '#');
            id = Integer.parseInt(symbolArray[1], 10);
        } catch (Exception ex) {
            throw new InvalidFungibleBalanceException(String.format("Failed to parse symbol id %s", parts[1]), ex);
        }

        Symbol localSymbol = symbol;

        if (symbol == null) {
            localSymbol = Symbol.of(id, precision);
        }


        float balanceInFloat = Float.parseFloat(parts[0]) * (int) Math.pow(10.0, localSymbol.getPrecision());

        return Asset.of(localSymbol, (int) balanceInFloat);
    }

    public Symbol getSymbol() {
        return symbol;
    }

    @Override
    public String toString() {
        double balanceInDouble = (double) balance / (int) Math.pow(10.0, symbol.getPrecision());
        return String.format("%." + symbol.getPrecision() + "f S#%d", balanceInDouble, symbol.getId());
    }
}
