package io.everitoken.sdk.java.apiResources;

public class Info extends ApiResource {
    private static final String name = "info";
    private static final String uri = "/v1/chain/get_info";
    private static final String method = "GET";

    public Info() {
        super(name, uri, method);
    }
}
