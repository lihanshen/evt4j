package io.everitoken.sdk.java.model;

public class TokenName {
    private final String name;
    private final String domain;

    public TokenName(String name, String domain) {
        this.name = name;
        this.domain = domain;
    }

    public String getName() {
        return name;
    }

    public String getDomain() {
        return domain;
    }
}
