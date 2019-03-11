package io.everitoken.sdk.java.abi;

import com.alibaba.fastjson.annotation.JSONField;
import io.everitoken.sdk.java.dto.AuthorizerRef;
import io.everitoken.sdk.java.dto.PushableAction;

public class AddMetaAction extends Abi implements PushableAction {

    @JSONField(deserialize = false, serialize = false)
    private static final String name = "addmeta";

    private final String metaKey;
    private final String metaValue;
    private final AuthorizerRef creator;


    private AddMetaAction(String key, String domain, String metaKey, String metaValue, AuthorizerRef creator) {
        super(name, key, domain);
        this.metaKey = metaKey;
        this.metaValue = metaValue;
        this.creator = creator;
    }

    public static AddMetaAction ofGroup(String key, String metaKey, String metaValue, AuthorizerRef creator) {
        return new AddMetaAction(key, ".group", metaKey, metaValue, creator);
    }

    public static AddMetaAction ofFungible(String key, String metaKey, String metaValue, AuthorizerRef creator) {
        return new AddMetaAction(key, ".fungible", metaKey, metaValue, creator);
    }

    public static AddMetaAction ofDomainToken(String key, String domain, String metaKey, String metaValue,
                                              AuthorizerRef creator) {
        return new AddMetaAction(key, domain, metaKey, metaValue, creator);
    }

    @Override
    @JSONField(deserialize = false, serialize = false)
    public String getKey() {
        return super.getKey();
    }

    @JSONField(name = "key")
    public String getMetaKey() {
        return metaKey;
    }

    @JSONField(name = "value")
    public String getMetaValue() {
        return metaValue;
    }

    @Override
    @JSONField(deserialize = false, serialize = false)
    public String getDomain() {
        return super.getDomain();
    }

    public String getCreator() {
        return creator.toString();
    }
}
