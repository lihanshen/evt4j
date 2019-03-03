package io.everitoken.sdk.java.dto;

import io.everitoken.sdk.java.PublicKey;
import org.jetbrains.annotations.Nullable;

public class AuthorizerRef {

    private static final String ACCOUNT_IDENTIFIER = "[A]";
    private static final String GROUP_IDENTIFIER = "[G]";
    private PublicKey publicKey;
    private String type;
    private boolean isOwner;

    private AuthorizerRef(String type, boolean isOwner, @Nullable PublicKey publicKey) {
        this.publicKey = publicKey;
        this.type = type;
        this.isOwner = isOwner;
    }

    public static AuthorizerRef createAccount(PublicKey publicKey) {
        return new AuthorizerRef(ACCOUNT_IDENTIFIER, false, publicKey);
    }

    public static AuthorizerRef createGroup(PublicKey publicKey) {
        return new AuthorizerRef(GROUP_IDENTIFIER, false, publicKey);
    }

    public static AuthorizerRef createGroup() {
        return new AuthorizerRef(GROUP_IDENTIFIER, true, null);
    }

    @Override
    public String toString() {
        if (isOwner) {
            return String.format("%s %s", GROUP_IDENTIFIER, ".OWNER");
        }

        return String.format("%s %s", type, publicKey.toString());
    }
}
