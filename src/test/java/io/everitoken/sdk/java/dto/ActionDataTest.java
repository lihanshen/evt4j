package io.everitoken.sdk.java.dto;

import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ActionDataTest {

    @Test
    @DisplayName("Should throw correct exception")
    void throwCorrectException() {
        Assertions.assertThrows(JSONException.class, () -> ActionData.create(new JSONObject()));
    }

}