package io.everitoken.sdk.java.abi;

import io.everitoken.sdk.java.params.NetParams;
import io.everitoken.sdk.java.params.TestNetNetParams;
import org.json.JSONObject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

class RemoteAbiSerialisationProviderTest {

    @Test
    @Tag("Integration")
//    @EnabledIfEnvironmentVariable(named = "ENV", matches = "INTEGRATION")
    @DisplayName("Serialize Abi via node")
    void serializeViaAbi() {
        NetParams netParams = new TestNetNetParams();
        String data = "{\"action\":\"newgroup\",\"args\":{\"name\":\"testgroupcreationfei\",\"group\":{\"name\":" +
                "\"testgroupcreationfei\",\"key\":\"EVT6Qz3wuRjyN6gaU3P3XRxpnEZnM4oPxortemaWDwFRvsv2FxgND\"," +
                "\"root\":{\"threshold\":6,\"weight\":0,\"nodes\":[{\"threshold\":1,\"weight\":3,\"nodes\":" +
                "[{\"key\":\"EVT6MRyAjQq8ud7hVNYcfnVPJqcVpscN5So8BhtHuGYqET5GDW5CV\",\"weight\":1},{\"key\"" +
                ":\"EVT8MGU4aKiVzqMtWi9zLpu8KuTHZWjQQrX475ycSxEkLd6aBpraX\",\"weight\":1}]},{\"key\":" +
                "\"EVT8MGU4aKiVzqMtWi9zLpu8KuTHZWjQQrX475ycSxEkLd6aBpraX\",\"weight\":3},{\"threshold\":" +
                "1,\"weight\":3,\"nodes\":[{\"key\":\"EVT6MRyAjQq8ud7hVNYcfnVPJqcVpscN5So8BhtHuGYqET5GDW5CV\"," +
                "\"weight\":" +
                "1},{\"key\":\"EVT8MGU4aKiVzqMtWi9zLpu8KuTHZWjQQrX475ycSxEkLd6aBpraX\",\"weight\":1}]}]}}}}";
        String newDomain = "{\"args\":\"{\"name\":\"testingTmpData\"," +
                "\"creator\":\"EVT6Qz3wuRjyN6gaU3P3XRxpnEZnM4oPxortemaWDwFRvsv2FxgND\"," +
                "\"issue\":{\"name\":\"issue\",\"threshold\":1," +
                "\"authorizers\":[{\"ref\":\"[A] EVT6Qz3wuRjyN6gaU3P3XRxpnEZnM4oPxortemaWDwFRvsv2FxgND\"," +
                "\"weight\":1}]},\"transfer\":{\"name\":\"transfer\",\"threshold\":1," +
                "\"authorizers\":[{\"ref\":\"[G] .OWNER\",\"weight\":1}]}," +
                "\"manage\":{\"name\":\"manage\",\"threshold\":1," +
                "\"authorizers\":[{\"ref\":\"[A] " +
                "EVT6Qz3wuRjyN6gaU3P3XRxpnEZnM4oPxortemaWDwFRvsv2FxgNDy\",\"weight\":1}]}}\"," +
                "\"action\":\"newdomain\"}";

        RemoteAbiSerialisationProvider provider = new RemoteAbiSerialisationProvider(netParams);
        JSONObject res = new JSONObject(provider.serialize(data));
        Assertions.assertTrue(res.has("binargs"));
    }
}