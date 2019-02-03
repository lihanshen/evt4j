package io.everitoken.sdk.java.abi;

import io.everitoken.sdk.java.params.MainNetNetParams;
import io.everitoken.sdk.java.params.NetParams;
import org.json.JSONObject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class NewDomainActionTest {
    private final String data = "{\"name\":\"test111\"," +
            "\"creator\":\"EVT6Qz3wuRjyN6gaU3P3XRxpnEZnM4oPxortemaWDwFRvsv2FxgND\",\"issue\":{\"name\":\"issue\"," +
            "\"threshold\":1,\"authorizers\":[{\"ref\":\"[A] EVT6Qz3wuRjyN6gaU3P3XRxpnEZnM4oPxortemaWDwFRvsv2FxgND\"," +
            "\"weight\":1}]},\"transfer\":{\"name\":\"transfer\",\"threshold\":1,\"authorizers\":[{\"ref\":\"[G] " +
            ".OWNER\",\"weight\":1}]},\"manage\":{\"name\":\"manage\",\"threshold\":1,\"authorizers\":[{\"ref\":\"[A]" +
            " EVT6Qz3wuRjyN6gaU3P3XRxpnEZnM4oPxortemaWDwFRvsv2FxgND\",\"weight\":1}]}}";
    private final String dataSerialisedByAdi =
            "7d90f70dc30000000002c8f031561c4758c9551cff47246f2c347189fe684c04da35cf88e813f810e3c2000000008052e74c01000" +
                    "00001010002c8f031561c4758c9551cff47246f2c347189fe684c04da35cf88e813f810e3c20100000000b298e982a40100" +
                    "000001000001000000000094135c680100000001010002c8f031561c4758c9551cff47246f2c347189fe684c04da35cf" +
                    "88e813f810e3c20100";

    @Test
    @DisplayName("Able to serialize via api")
    void serialize() {
        Assertions.assertDoesNotThrow(() -> {
            JSONObject json = new JSONObject(data);
            NewDomainAction newDomainAction = NewDomainAction.ofRaw(
                    json.getString("name"),
                    json.getString("creator"),
                    json.getJSONObject("issue"),
                    json.getJSONObject("transfer"),
                    json.getJSONObject("manage")
            );

            NetParams netParams = new MainNetNetParams(NetParams.NET.MAINNET1);
            RemoteAbiSerialisationProvider provider = new RemoteAbiSerialisationProvider(netParams);
            JSONObject res = new JSONObject(newDomainAction.serialize(provider));
            Assertions.assertEquals(dataSerialisedByAdi, res.get("data"));
        });
    }
}