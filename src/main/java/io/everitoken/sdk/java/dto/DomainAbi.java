package io.everitoken.sdk.java.dto;

import io.everitoken.sdk.java.PublicKey;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.json.JSONObject;

import java.util.Objects;

public class DomainAbi implements Namable, DomainKeyConfiguration, TransactionalAction, AbiImpl {
    private final String name;
    private final PublicKey creator;
    private final Permission issue;
    private final Permission transfer;
    private final Permission manage;
    private String domainAction = ".create";

    protected DomainAbi(String name, PublicKey creator, Permission issue, Permission transfer, Permission manage) {
        this.name = name;
        this.creator = creator;
        this.issue = issue;
        this.transfer = transfer;
        this.manage = manage;
    }

    protected DomainAbi(String name, String creator, JSONObject issue, JSONObject transfer, JSONObject manage) {
        this.name = name;
        this.creator = PublicKey.of(creator);
        this.issue = Permission.ofRaw(issue);
        this.transfer = Permission.ofRaw(transfer);
        this.manage = Permission.ofRaw(manage);
    }

    @NotNull
    @Contract("_, _, _, _, _ -> new")
    public static DomainAbi ofRaw(String name, String creator, JSONObject issue, JSONObject transfer, JSONObject manage) {
        Objects.requireNonNull(issue);
        Objects.requireNonNull(transfer);
        Objects.requireNonNull(manage);

        return new DomainAbi(name, creator, issue, transfer, manage);
    }

    @Override
    public String getName() {
        return name;
    }

    public String getCreator() {
        return creator.toString();
    }

    public Permission getTransfer() {
        return transfer;
    }

    public Permission getIssue() {
        return issue;
    }

    public Permission getManage() {
        return manage;
    }

    public void setDomainAction(String domainAction) {
        this.domainAction = domainAction;
    }

    @Override
    public JSONObject getDomainKeyConfiguration() {
        return Utils.buildDomainKeyJson(getName(), domainAction);
    }

    @Override
    public String serialize() {
        return null;
    }
}

