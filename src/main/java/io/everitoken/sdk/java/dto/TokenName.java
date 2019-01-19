package io.everitoken.sdk.java.dto;

public class TokenName extends NameableResource {
    private final String domain;

    public TokenName(String name, String domain) {
        super(name);
        this.domain = domain;
    }

    public String getDomain() {
        return domain;
    }

    @Override
    public String toString() {
        return String.format("%s, %s -> %s", super.toString(), "Domain", getDomain());
    }
}