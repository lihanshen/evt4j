package io.everitoken.sdk.java.abi;

import com.alibaba.fastjson.annotation.JSONField;
import io.everitoken.sdk.java.EvtLink;
import io.everitoken.sdk.java.dto.PushableAction;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.nio.charset.StandardCharsets;
import java.util.Optional;

public class EveriPassAction extends Abi implements PushableAction {
    @JSONField(deserialize = false, serialize = false)
    private static final String name = "everipass";

    private final String link;

    private EveriPassAction(@NotNull String link, @NotNull String domain, @NotNull String tokenName) {
        super(name, tokenName, domain);
        this.link = link;
    }

    @Contract("_, _, _ -> new")
    @NotNull
    public static EveriPassAction of(String link) {
        EvtLink.ParsedLink parsedLink = EvtLink.parseLink(link, false);

        // sanity control to make sure the link is for everipay
        if ((parsedLink.getFlag() & 2) != 2) {
            throw new IllegalArgumentException("Invalid EvtLink: This link is not for everiPass");
        }

        // get symbol from link
        Optional<EvtLink.Segment> domainSegment =
                parsedLink.getSegments().stream().filter(segment -> segment.getTypeKey() == 91).findFirst();

        Optional<EvtLink.Segment> tokenSegment =
                parsedLink.getSegments().stream().filter(segment -> segment.getTypeKey() == 92).findFirst();

        if (!domainSegment.isPresent()) {
            throw new IllegalArgumentException("Failed to parse EveriPass link to extract \"domain\"");
        }

        if (!tokenSegment.isPresent()) {
            throw new IllegalArgumentException("Failed to parse EveriPass link to extract \"token name\"");
        }

        String domain = new String(domainSegment.get().getContent(), StandardCharsets.UTF_8);
        String tokenName = new String(tokenSegment.get().getContent(), StandardCharsets.UTF_8);

        return new EveriPassAction(link, domain, tokenName);
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

    public String getLink() {
        return link;
    }
}
