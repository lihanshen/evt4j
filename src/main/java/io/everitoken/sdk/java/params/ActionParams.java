package io.everitoken.sdk.java.params;

import com.alibaba.fastjson.JSON;

public class ActionParams implements ApiParams, Paginatable {
    private final String domain;
    private final String key;
    private final String[] names;
    private final int skip;
    private final int take;

    public ActionParams(String domain, String key, String[] names, int skip, int take) {
        this.domain = domain;
        this.key = key;
        this.names = names;
        this.skip = skip;
        this.take = take;
    }

    public ActionParams(String domain) {
        this(domain, null, new String[]{}, 0, 10);
    }

    public ActionParams(String domain, String key) {
        this(domain, key, new String[]{}, 0, 10);
    }

    public ActionParams(String domain, String key, String[] names) {
        this(domain, key, names, 0, 10);
    }

    @Override
    public String asBody() {
        return JSON.toJSONString(this);
    }

    @Override
    public int getSkip() {
        return skip;
    }

    @Override
    public int getTake() {
        return take;
    }

    public String getDomain() {
        return domain;
    }

    public String getKey() {
        return key;
    }

    public String[] getNames() {
        return names;
    }
}
