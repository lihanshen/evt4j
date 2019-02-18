package io.everitoken.sdk.java;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class SymbolTest {

    @Test
    void init() {
        Symbol symbol = Symbol.of(2, 5);
        Assertions.assertEquals("5,S#2", symbol.toString());
    }

    @Test
    void staticEvtSymbol() {
        Assertions.assertEquals("5,S#1", Symbol.Evt.toString());
    }

    @Test
    void throwWithInvalidPrecision() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> Symbol.of(1, 18));
    }
}