package io.everitoken.sdk.java.model;

import io.everitoken.sdk.java.EvtSdkException;
import io.everitoken.sdk.java.PublicKey;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class TokenDetailData extends NameableResource {
    private String domain;
    private List<PublicKey> owners;
    private JSONArray metas;

    public TokenDetailData(JSONObject raw) {
        super(raw.getString("name"));
        domain = raw.getString("domain");
        metas = raw.getJSONArray("metas");

        JSONArray owner = raw.getJSONArray("owner");
        owners = StreamSupport.stream(owner.spliterator(), true).map(publicKey -> {
            try {
                return new PublicKey((String) publicKey);
            } catch (EvtSdkException ex) {
                return null;
            }
        }).filter(key -> !Objects.isNull(key)).collect(Collectors.toList());
    }

    public String getDomain() {
        return domain;
    }

    public List<PublicKey> getOwners() {
        return owners;
    }

    public JSONArray getMetas() {
        return metas;
    }

    public String toString() {
        return String.format("%s, %s -> %s, %s -> %s", super.toString(), "Domain", getDomain(), "owners",
                             owners.stream().map(PublicKey::toString).reduce(String::concat)
        );
    }
}