package io.everitoken.sdk.java.apiResource;

import io.everitoken.sdk.java.param.NetParams;
import io.everitoken.sdk.java.param.TestNetNetParams;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

class ApiResourceTest {

    @Test
    void get() {
        // mock Unirest to test
    }

    @Test
    void getUrl() {
        ApiResource info = new Info();
        NetParams netParams = new TestNetNetParams();
        String url = info.getUrl(netParams);
        assertTrue(url.contains("testnet1.everitoken.io"), "url of getInfo");
        assertTrue(url.endsWith("/v1/chain/get_info"), "url of getInfo");
    }

    @Test
    void equals() {
        // TODO test not equal
        ApiResource info = new Info();
        ApiResource info1 = new Info();
        assertTrue(info.equals(info1), "Should equal");
    }
}