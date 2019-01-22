package io.everitoken.sdk.java.dto;

import io.everitoken.sdk.java.exceptions.InvalidFungibleBalanceException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class FungibleBalanceDataTest {
    @Test
    @DisplayName("Using constructor")
    void usingConstructor() {
        FungibleBalanceData balance = new FungibleBalanceData(1, 20.1);
        System.out.println(balance.toString());
        System.out.println(balance.getRaw());
    }

    @Test
    @DisplayName("Parse correct input")
    void parseCorrectBalance() {
        Assertions.assertDoesNotThrow(() -> {
            String balance = "234.23845 S#10";
            FungibleBalanceData fungibleBalance = new FungibleBalanceData(balance);
            assertEquals(String.format("%.5f", fungibleBalance.getBalance()), String.format("%.5f", 234.23845),
                         "balance should be the same"
            );
            assertEquals(fungibleBalance.getId(), 10);
            assertEquals(fungibleBalance.getRaw(), balance);
        });
    }

    @Test
    @DisplayName("Invalid inputs")
    void invalidInputs() {
        Assertions.assertThrows(InvalidFungibleBalanceException.class, () -> {
            String balance = "";
            new FungibleBalanceData(balance);
        });
        Assertions.assertThrows(InvalidFungibleBalanceException.class, () -> {
            String balance = "whatnot S#10";
            new FungibleBalanceData(balance);
        });
        Assertions.assertThrows(InvalidFungibleBalanceException.class, () -> {
            String balance = "S#10";
            new FungibleBalanceData(balance);
        });
        Assertions.assertThrows(InvalidFungibleBalanceException.class, () -> {
            String balance = "20.00 10";
            new FungibleBalanceData(balance);
        });
    }
}