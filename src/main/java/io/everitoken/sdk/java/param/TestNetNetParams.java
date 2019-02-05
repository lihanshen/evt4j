package io.everitoken.sdk.java.param;

import com.mashape.unirest.http.Unirest;
import org.apache.http.impl.client.DefaultHttpRequestRetryHandler;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;

public class TestNetNetParams extends NetParams {
    public TestNetNetParams() {
        super("http", NET.TESTNET.getUrl(), 8888, 15000);

        HttpClientBuilder clientBuilder = HttpClients.custom();
        clientBuilder.setRetryHandler(new DefaultHttpRequestRetryHandler(5, false));
        Unirest.setHttpClient(clientBuilder.build());
    }
}
