package io.everitoken.sdk.java.apiResources;

public class HeadBlockHeaderState extends ApiResource {
    private static final String name = "headBlockHeaderState";
    private static final String uri = "/v1/chain/get_head_block_header_state";
    private static final String method = "GET";

    public HeadBlockHeaderState() {
        super(name, uri, method);
    }
}
