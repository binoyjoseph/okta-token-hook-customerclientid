package com.okta.examples.apiamhooks.model.hooks;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class TokenPatchResponse {

    private String type;
    private List<Value> value = new ArrayList<>();

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Value {

        private String op;
        private String path;
        private Object value;
    }
}
