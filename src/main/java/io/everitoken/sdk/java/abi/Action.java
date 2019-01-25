package io.everitoken.sdk.java.abi;

// TODO write test
public class Action {
    private final String name;
    private final String key;
    private final String domain;
    private final String data;

    private Action(String name, String key, String domain, String data) {
        this.name = name;
        this.key = key;
        this.domain = domain;
        this.data = data;
    }

    private Action(String name, String key, String domain) {
        this.name = name;
        this.key = key;
        this.domain = domain;
        data = null;
    }


    // setters needs to support async actions
}
