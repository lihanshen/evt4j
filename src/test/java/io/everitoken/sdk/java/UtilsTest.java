package io.everitoken.sdk.java;

import io.everitoken.sdk.java.exceptions.Base58CheckException;
import io.everitoken.sdk.java.params.NetParams;
import io.everitoken.sdk.java.params.TestNetNetParams;
import org.json.JSONObject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.EnabledIfEnvironmentVariable;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class UtilsTest {

    @Test
    @DisplayName("Base58 Invalid Key")
    void base58CheckDecodeWithInvalidKey() {
        Assertions.assertThrows(Base58CheckException.class, () -> {
            String key = "76uLwUD5t6fkob9Rbc11UxHgdTVshNceyv2hmppw4d82j2zYRpa";
            Utils.base58CheckDecode(key);
        });
    }

    @Test
    @DisplayName("Base58 Valid Key, Checksum successful")
    void base58CheckDecodeWithValidKey() {
        Assertions.assertDoesNotThrow(() -> {
            Utils.base58CheckDecode("76uLwUD5t6fkob9Rbc9UxHgdTVshNceyv2hmppw4d82j2zYRpa");
        });
    }

    @Test
    void random32BytesAsHex() {
        String str32BytesInHex = Utils.random32BytesAsHex();
        assertEquals(64, str32BytesInHex.length(), "Message should be 64 characters");
    }

    @Test
    void random32Bytes() {
        byte[] bytes32 = Utils.random32Bytes();
        assertEquals(32, bytes32.length, "Message should be 32 bytes long");
    }

    @Test
    @Tag("Integration")
    @EnabledIfEnvironmentVariable(named = "ENV", matches = "INTEGRATION")
    void abiToBin() {
        Assertions.assertDoesNotThrow(() -> {
            NetParams testNetParams = new TestNetNetParams();
            JSONObject res = Utils.abiToBin(
                    testNetParams,
                    () -> "{\"action\":\"newgroup\",\"args\":{\"name\":\"testgroupcreationfei\",\"group\":{\"name\":" +
                            "\"testgroupcreationfei\",\"key\":\"EVT6Qz3wuRjyN6gaU3P3XRxpnEZnM4oPxortemaWDwFRvsv2FxgND\"," +
                            "\"root\":{\"threshold\":6,\"weight\":0,\"nodes\":[{\"threshold\":1,\"weight\":3,\"nodes\":" +
                            "[{\"key\":\"EVT6MRyAjQq8ud7hVNYcfnVPJqcVpscN5So8BhtHuGYqET5GDW5CV\",\"weight\":1},{\"key\"" +
                            ":\"EVT8MGU4aKiVzqMtWi9zLpu8KuTHZWjQQrX475ycSxEkLd6aBpraX\",\"weight\":1}]},{\"key\":" +
                            "\"EVT8MGU4aKiVzqMtWi9zLpu8KuTHZWjQQrX475ycSxEkLd6aBpraX\",\"weight\":3},{\"threshold\":" +
                            "1,\"weight\":3,\"nodes\":[{\"key\":\"EVT6MRyAjQq8ud7hVNYcfnVPJqcVpscN5So8BhtHuGYqET5GDW5CV\",\"weight\":" +
                            "1},{\"key\":\"EVT8MGU4aKiVzqMtWi9zLpu8KuTHZWjQQrX475ycSxEkLd6aBpraX\",\"weight\":1}]}]}}}}",
                    true
            );
            assertTrue(res.has("binargs"));
        });
    }
}