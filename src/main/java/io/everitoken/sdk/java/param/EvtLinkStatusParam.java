package io.everitoken.sdk.java.param;

import org.jetbrains.annotations.NotNull;

public class EvtLinkStatusParam {
    private boolean block;
    private boolean throwException;
    private String linkId;

    private EvtLinkStatusParam(@NotNull String linkId, boolean block, boolean throwException) {
        this.block = block;
        this.throwException = throwException;
        this.linkId = linkId;
    }

    public static EvtLinkStatusParam of(@NotNull String linkId) {
        return new EvtLinkStatusParam(linkId, true, false);
    }

    public static EvtLinkStatusParam of(@NotNull String linkId, boolean block, boolean throwException) {
        return new EvtLinkStatusParam(linkId, block, throwException);
    }

    public boolean isBlock() {
        return block;
    }

    public boolean isThrowException() {
        return throwException;
    }

    public String getLinkId() {
        return linkId;
    }
}
