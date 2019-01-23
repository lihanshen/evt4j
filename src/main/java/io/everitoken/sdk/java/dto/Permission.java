package io.everitoken.sdk.java.dto;

import com.alibaba.fastjson.annotation.JSONField;
import org.jetbrains.annotations.NotNull;
import org.json.JSONObject;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class Permission implements Namable {
    private final String name;
    private final int threshold;
    private final List<AuthorizerWeight> authorizers;

    private Permission(String name, int threshold, List<AuthorizerWeight> authorizers) {
        this.name = name;
        this.threshold = threshold;
        this.authorizers = authorizers;
    }

    @NotNull
    static Permission ofRaw(@NotNull JSONObject raw) {
        Objects.requireNonNull(raw);
        String name = raw.getString("name");
        int threshold = raw.getInt("threshold");
        List<AuthorizerWeight> authorizers = StreamSupport
                .stream(raw.getJSONArray("authorizers").spliterator(), true)
                .map(json -> AuthorizerWeight.ofRaw((JSONObject) json)).collect(Collectors.toList());

        return new Permission(name, threshold, authorizers);
    }

    @JSONField(ordinal = 1)
    public int getThreshold() {
        return threshold;
    }

    @JSONField(ordinal = 2)
    public List<AuthorizerWeight> getAuthorizers() {
        return authorizers;
    }

    @Override
    public String getName() {
        return name;
    }
}
