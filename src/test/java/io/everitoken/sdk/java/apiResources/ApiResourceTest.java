package io.everitoken.sdk.java.apiResources;

import io.everitoken.sdk.java.params.NetParams;
import io.everitoken.sdk.java.params.TestNetNetParams;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class ApiResourceTest {

    @Test
    public void get() {
        // mock Unirest to test
    }

    @Test
    public void getUrl() {
        ApiResource info = new Info();
        NetParams netParams = new TestNetNetParams();
        String url = info.getUrl(netParams);
        assertTrue("url of getInfo", url.contains("testnet1.everitoken.io"));
        assertTrue("url of getInfo", url.endsWith("/v1/chain/get_info"));
    }

    @Test
    public void equals() {
        // TODO test not equal
        ApiResource info = new Info();
        ApiResource info1 = new Info();
        assertTrue("Should equal", info.equals(info1));
    }
}