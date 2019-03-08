package io.everitoken.sdk.java.abi;

import com.alibaba.fastjson.annotation.JSONField;
import io.everitoken.sdk.java.Address;
import io.everitoken.sdk.java.Asset;
import io.everitoken.sdk.java.EvtLink;
import io.everitoken.sdk.java.dto.PushableAction;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.nio.ByteBuffer;
import java.util.Optional;

public class EveriPayAction extends Abi implements PushableAction {

    @JSONField(deserialize = false, serialize = false)
    private static final String domain = ".fungible";

    @JSONField(deserialize = false, serialize = false)
    private static final String name = "everipay";

    private final String link;
    private final Asset asset;
    private final Address payee;

    private EveriPayAction(@NotNull String link, @NotNull String symbolId, @NotNull Asset asset,
                           @NotNull Address payee) {
        super(name, symbolId, domain);
        this.asset = asset;
        this.payee = payee;
        this.link = link;
    }

    @Contract("_, _, _ -> new")
    @NotNull
    public static EveriPayAction of(String link, String asset, String payee) {
        EvtLink.ParsedLink parsedLink = EvtLink.parseLink(link, false);

        // sanity control to make sure the link is for everipay
        if (!EvtLink.ParsedLink.isEveriPay(parsedLink)) {
            throw new IllegalArgumentException("Invalid EvtLink: This link is not for everiPay");
        }

        // get symbol from link
        Optional<EvtLink.Segment> symbolIdSegment =
                parsedLink.getSegments().stream().filter(segment -> segment.getTypeKey() == 44).findFirst();

        if (!symbolIdSegment.isPresent()) {
            throw new IllegalArgumentException("Failed to parse EveriPay link to extract symbolId");
        }

        int symbolId = ByteBuffer.allocate(4).put(symbolIdSegment.get().getContent()).getInt(0);

        return new EveriPayAction(link, Integer.toString(symbolId),
                                  Asset.parseFromRawBalance(asset),
                                  Address.of(payee)
        );
    }

    @Override
    @JSONField(deserialize = false, serialize = false)
    public String getDomain() {
        return super.getDomain();
    }

    @Override
    @JSONField(deserialize = false, serialize = false)
    public String getKey() {
        return super.getKey();
    }

    @JSONField(name = "number")
    public String getAsset() {
        return asset.toString();
    }

    public String getLink() {
        return link;
    }

    public String getPayee() {
        return payee.toString();
    }
}
