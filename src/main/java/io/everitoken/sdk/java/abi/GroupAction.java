package io.everitoken.sdk.java.abi;

import com.alibaba.fastjson.annotation.JSONField;
import io.everitoken.sdk.java.dto.PushableAction;
import org.json.JSONObject;

public class GroupAction extends Abi implements PushableAction {
    @JSONField(deserialize = false, serialize = false)
    private static final String domain = ".group";
    @JSONField(deserialize = false, serialize = false)

    private final JSONObject groupDef;

    protected GroupAction(String name, String groupName, JSONObject groupDef) {
        super(name, groupName, domain);
        this.groupDef = groupDef;
    }

    @Override
    public String getData(AbiSerialisationProviderInterface provider) {
        JSONObject payload = new JSONObject();
        payload.put("name", getKey());
        payload.put("group", groupDef);

        return provider.serialize(AbiToBin.pack(getName(), payload));
    }
}
