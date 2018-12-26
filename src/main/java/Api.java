import io.everitoken.sdk.java.keyProvider.KeyProvider;
import io.everitoken.sdk.java.params.Params;
import io.everitoken.sdk.java.params.TestNetParams;

import javax.annotation.Nullable;

public class Api {
    private Params params;
    private KeyProvider keyProvider;

    public Api(Params params, @Nullable KeyProvider keyProvider) {
        params = params;
        keyProvider = keyProvider;
    }

    public Api() {
        this(new TestNetParams(), null);
    }
}
