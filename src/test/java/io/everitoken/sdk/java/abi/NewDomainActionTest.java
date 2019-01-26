package io.everitoken.sdk.java.abi;

import io.everitoken.sdk.java.params.NetParams;
import io.everitoken.sdk.java.params.TestNetNetParams;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;

class NewDomainActionTest {
    private final String data = "{\"name\":\"test111\"," +
            "\"creator\":\"EVT6Qz3wuRjyN6gaU3P3XRxpnEZnM4oPxortemaWDwFRvsv2FxgND\",\"issue\":{\"name\":\"issue\"," +
            "\"threshold\":1,\"authorizers\":[{\"ref\":\"[A] EVT6Qz3wuRjyN6gaU3P3XRxpnEZnM4oPxortemaWDwFRvsv2FxgND\"," +
            "\"weight\":1}]},\"transfer\":{\"name\":\"transfer\",\"threshold\":1,\"authorizers\":[{\"ref\":\"[G] " +
            ".OWNER\",\"weight\":1}]},\"manage\":{\"name\":\"manage\",\"threshold\":1,\"authorizers\":[{\"ref\":\"[A]" +
            " EVT6Qz3wuRjyN6gaU3P3XRxpnEZnM4oPxortemaWDwFRvsv2FxgND\",\"weight\":1}]}}";

    @Test
    void serialize() {
        JSONObject json = new JSONObject(data);
        NewDomainAction newDomainAction = NewDomainAction.ofRaw(
                json.getString("name"),
                json.getString("creator"),
                json.getJSONObject("issue"),
                json.getJSONObject("transfer"),
                json.getJSONObject("manage")
        );

        NetParams netParams = new TestNetNetParams();
        RemoteAbiSerialisationProvider provider = new RemoteAbiSerialisationProvider(netParams);
        System.out.println(newDomainAction.serialize(provider));
    }
}