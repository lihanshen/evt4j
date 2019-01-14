package io.everitoken.sdk.java;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

class ApiTest {
    private Api api;

    @BeforeEach
    void setUp() {
        api = new Api();
    }

    @Test
    @DisplayName("Ensure interfaces")
    void ensureInterfaces() {
        // TODO: implement
        assertTrue(true, "TODO");
    }
}