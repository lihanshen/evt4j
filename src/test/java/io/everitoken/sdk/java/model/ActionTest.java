package io.everitoken.sdk.java.model;

import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ActionTest {

    @Test
    @DisplayName("Should throw correct exception")
    void throwCorrectException() {
        Assertions.assertThrows(JSONException.class, () -> Action.create(new JSONObject()));
    }

}