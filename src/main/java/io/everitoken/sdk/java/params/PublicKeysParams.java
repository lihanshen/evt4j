package io.everitoken.sdk.java.params;

import io.everitoken.sdk.java.PublicKey;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class PublicKeysParams implements ApiParams {
    private List<PublicKey> publicKeys = new ArrayList<>();

    public PublicKeysParams(String[] publicKeys) {
        for (String publicKey : publicKeys) {
            this.publicKeys.add(new PublicKey(publicKey));
        }
    }

    public PublicKeysParams(List<PublicKey> publicKeys) {
        this.publicKeys = publicKeys;
    }

    public JSONObject asJson() {
        String paramObjectKey = "keys";
        JSONObject jsonObject = new JSONObject();
        JSONArray keys =
                new JSONArray(publicKeys.stream().map(PublicKey::toString).collect(Collectors.toList()));

        jsonObject.put(paramObjectKey, keys);

        return jsonObject;
    }
}
