package io.everitoken.sdk.java.dto;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import io.everitoken.sdk.java.PublicKey;

class AuthorizerRefTest {
    private String publicKeyString = "EVT76uLwUD5t6fkob9Rbc9UxHgdTVshNceyv2hmppw4d82j2zYRpa";

    @Test
    void tesCreateOwner() {
        AuthorizerRef group = AuthorizerRef.createGroup();
        Assertions.assertEquals("[G] .OWNER", group.toString());
    }

    @Test
    void testCreateAccount() {
        AuthorizerRef account = AuthorizerRef.createAccount(PublicKey.of(publicKeyString));
        Assertions.assertEquals("[A] " + publicKeyString, account.toString());
    }

    @Test
    void testCreateGroup() {
        AuthorizerRef group = AuthorizerRef.createGroup(PublicKey.of(publicKeyString));
        Assertions.assertEquals("[G] " + publicKeyString, group.toString());
    }
}