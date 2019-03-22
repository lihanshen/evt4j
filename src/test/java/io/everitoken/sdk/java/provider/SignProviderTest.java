package io.everitoken.sdk.java.provider;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import io.everitoken.sdk.java.PrivateKey;
import io.everitoken.sdk.java.Signature;
import io.everitoken.sdk.java.Utils;

class SignProviderTest {
    @Test
    @DisplayName("Empty keys yield empty signatures")
    void assertEmptyKeyProvider() {
        SignProvider signProvider = SignProvider.of(ArrayList::new);
        Assertions.assertEquals(new ArrayList<Signature>(), signProvider.sign(new byte[] {}));
    }

    @Test
    @DisplayName("Valid key provider produces valid signature")
    void singleKey() {
        String validPrivateKey = "5J1by7KRQujRdXrurEsvEr2zQGcdPaMJRjewER6XsAR2eCcpt3D";
        SignProvider signProvider = SignProvider.of(() -> Collections.singletonList(PrivateKey.of(validPrivateKey)));
        List<Signature> signatures = signProvider
                .sign(Utils.HEX.decode("08d576d1aa63a53daa610744989eb1997506c2dd9a86af67af51707ea81b8dae"));
        Assertions.assertEquals(
                "SIG_K1_KfdgiuhCZFSx9ggL4sNCoKnPzQwXEq1AJxEdd9Jw27GbuZ5ieoYMdh76FKpFEoxa8jVkFYMafyorxFHSutrgmFy8VbwCfD",
                signatures.get(0).toString());
    }

    @Test
    @DisplayName("Multiple keys will produce multiple signatures")
    void multipleKeys() {
        String privateKey1 = "5J1by7KRQujRdXrurEsvEr2zQGcdPaMJRjewER6XsAR2eCcpt3D";
        String privateKey2 = "5JV1kctxPzU3BdRENgRyDcUWQSqqzeckzjKXJWSkBoxXmXUCqKB";
        SignProvider signProvider = SignProvider
                .of(() -> Arrays.asList(PrivateKey.of(privateKey1), PrivateKey.of(privateKey2)));
        List<Signature> signatures = signProvider.sign(Utils.hash("helloworld".getBytes()));
        Assertions.assertEquals(2, signatures.size());
    }
}